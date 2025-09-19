package org.luke.service;

import org.luke.common.dal.model.Transaction;
import org.luke.common.dal.repository.TransactionRepository;
import org.luke.model.req.GetTransactionReq;
import org.luke.model.resp.GetTransactionResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadTestApiService {

    @Autowired
    private TransactionRepository transRepo;

    public GetTransactionResp getTransaction(GetTransactionReq req) {
        Transaction transDO = transRepo.selectTransaction(req.getTransId());

    }
}
