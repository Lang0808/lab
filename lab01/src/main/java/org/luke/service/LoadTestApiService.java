package org.luke.service;

import org.luke.common.dal.model.Product;
import org.luke.common.dal.model.Transaction;
import org.luke.common.dal.model.TransactionStatus;
import org.luke.common.dal.model.User;
import org.luke.common.dal.repository.ProductRepository;
import org.luke.common.dal.repository.TransactionRepository;
import org.luke.common.dal.repository.UserRepository;
import org.luke.common.model.exception.ErrorCode;
import org.luke.model.model.ProductInfo;
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

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductService productService;

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

    /**
     * Serves as a test to create transaction for db migration.
     * A real implementation for payment system will be done in next labs.
     *
     * @param req
     * @return
     */
    public BuyProductResp buyProduct(BuyProductReq req) {
        Product product = productService.getProductAndCheck(req);
        User user = userRepo.selectUser(req.getUserId());

        // Create and insert transaction with status OPEN
        Transaction transDO = createOpenTransaction(req, product);
        transRepo.insertTransaction(transDO);

        // Update user balances
        user.setBalance(user.getBalance() - transDO.getTotal());
        userRepo.updateUser(user);

        // Update transaction status to FINISH
        transDO.setStatus(TransactionStatus.FINISH);
        transRepo.updateTransaction(transDO);

        return successBuyProduct(transDO, product, user);
    }

    /**
     * Create {@link Transaction} with status {@link TransactionStatus#OPEN} for a {@link org.luke.model.req.BuyProductReq}
     */
    private Transaction createOpenTransaction (BuyProductReq req, Product product) {
        Transaction transaction = new Transaction();
        transaction.setId(req.getTransId());
        transaction.setSender(req.getUserId());
        transaction.setProductId(req.getProductId());
        transaction.setQuantity(req.getQuantity());
        transaction.setPrice(Long.parseLong(req.getPrice()));
        transaction.setTotal(transaction.getPrice() * transaction.getQuantity());
        transaction.setStatus(TransactionStatus.OPEN);
        return transaction;
    }

    private BuyProductResp successBuyProduct (Transaction transaction, Product product, User user) {
        BuyProductResp resp = new BuyProductResp();
        resp.setTransId(transaction.getId());
        resp.setTransStatus(transaction.getStatus().name());
        resp.setTotal(String.valueOf(transaction.getTotal()));
        return resp;
    }
}
