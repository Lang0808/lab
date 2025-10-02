package org.luke.generator;

import lombok.extern.slf4j.Slf4j;
import org.luke.common.dal.model.Transaction;
import org.luke.common.dal.model.TransactionStatus;
import org.luke.common.dal.repository.TransactionRepository;
import org.luke.common.util.IdUtil;
import org.luke.common.util.RandomUtil;
import org.luke.generator.config.OldDBDataGeneratorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
public class OldDBDataGenerator {

    private static final AtomicBoolean isRun = new AtomicBoolean(false);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OldDBDataGeneratorConfig genConfig;

    public Transaction generateDataTransaction(int i) {
        String transId = IdUtil.generateTransId(i);
        TransactionStatus status = RandomUtil.getRandomEnum(TransactionStatus.class);
        return generateDataTransaction(transId, status);
    }

    public Transaction generateDataTransaction(String transId, TransactionStatus status) {
        Transaction transaction = new Transaction();
        transaction.setId(transId);
        transaction.setPrice(Math.abs(RandomUtil.generateRandomLong()));
        transaction.setQuantity(Math.abs(RandomUtil.generateRandomInt()));
        transaction.setTotal(transaction.getPrice() * transaction.getQuantity());
        transaction.setSender(IdUtil.generateUserId(genConfig.getUser()));
        transaction.setReceiver(IdUtil.generateUserId(genConfig.getUser()));
        transaction.setProductId(IdUtil.generateProductId(genConfig.getProduct()));
        transaction.setStatus(status);
        return transaction;
    }

    public void generateTransaction(int i) {
        Transaction transaction = generateDataTransaction(i);
        transactionRepository.insertTransaction(transaction);
    }

    public void generateTransaction(String transId, TransactionStatus status) {
        Transaction transaction = generateDataTransaction(transId, status);
        transactionRepository.insertTransaction(transaction);
    }

    public static boolean tryRun() {
        return isRun.compareAndExchange(false, true);
    }

    public static boolean tryReleaseRun() {
        return isRun.compareAndExchange(true, false);
    }
}
