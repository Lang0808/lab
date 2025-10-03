import http from 'k6/http';
import { sleep, check } from 'k6';

const BASE_URL = 'http://host.docker.internal:8080';
const CREATE_URL = `${BASE_URL}/api/v1/lab01/old_db/create_transaction`;
const GET_URL = `${BASE_URL}/api/v1/lab01/api_test/transaction`;

const STATUSES = ['OPEN', 'PROCESSING', 'FAIL', 'FINISH'];
const TRANSACTION_ID_LENGTH = 20;
const NONEXIST_PREFIX = 'nonexist-';

function randomString(length) {
    const chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    let result = '';
    for (let i = 0; i < length; i++) {
        result += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return result;
}

export const options = {
    vus: 10, // 10 transactions per second
    duration: '1800s', // 30 mins
};

export default function () {
    // Insert transaction
    const transId = randomString(TRANSACTION_ID_LENGTH);
    const status = STATUSES[Math.floor(Math.random() * STATUSES.length)];
    const payload = JSON.stringify({ transId, status });
    const params = { headers: { 'Content-Type': 'application/json' }, timeout: '60s' };
    const res = http.post(CREATE_URL, payload, params);
    check(res, {
        'create transaction status is 200': (r) => r.status === 200,
    });
    let insertFailed = res.status !== 200;
    let getFailed = false;

    // if insert success, query transaction after 10 seconds
    if (res.status === 200) {
        sleep(10);
        const getRes = http.get(`${GET_URL}?transId=${transId}`, { timeout: '60s' });
        check(getRes, {
            'get transaction status is 200': (r) => r.status === 200,
        });
        if (getRes.status !== 200) {
            getFailed = true;
        }
    }

    // query non-existing transaction
    const nonexistId = NONEXIST_PREFIX + randomString(TRANSACTION_ID_LENGTH);
    const nonexistRes = http.get(`${GET_URL}?transId=${nonexistId}`, { timeout: '60s' });
    check(nonexistRes, {
        'get nonexist transaction should not be 200': (r) => r.status !== 200,
    });
    if (nonexistRes.status === 200) {
        // Count as failure
    }
}
