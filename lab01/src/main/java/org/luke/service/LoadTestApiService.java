package org.luke.service;

import org.luke.common.dal.model.Transaction;
import org.luke.common.dal.repository.TransactionRepository;
import org.luke.common.model.exception.ErrorCode;
import org.luke.model.model.TransactionInfo;
import org.luke.model.req.BuyProductReq;
import org.luke.model.resp.BuyProductResp;
import org.luke.model.resp.GetTransactionResp;
import org.luke.service.converter.TransactionConverter;
import org.luke.web.exception.model.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadTestApiService {

    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private TransactionConverter transConverter;

    public GetTransactionResp getTransaction(String transId) {
        Transaction transDO = transRepo.selectTransaction(transId);
        if (transDO == null) {
            throw new BaseException(ErrorCode.DATA_NOT_EXIST, "Transaction does not exist");
        }
        TransactionInfo transInfo = transConverter.toInfo(transDO);
        GetTransactionResp resp = new GetTransactionResp();
        resp.setTransInfo(transInfo);
        return resp;
    }

    public BuyProductResp buyProduct(BuyProductReq req) {
        Transaction transDO = createOpenTransaction(req);
    }

    private Transaction createOpenTransaction(BuyProductReq req) {
        Transaction transaction = new Transaction();
        transaction.setId(req.getTransId());
        transaction.setSender(req.getUserId());
        transaction.setQuantity(req.getQuantity());
        transaction.setPrice(req.getPrice());
    }
}
