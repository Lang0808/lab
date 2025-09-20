package org.luke.service.converter;

import org.luke.common.dal.model.Transaction;
import org.luke.common.util.TimeUtil;
import org.luke.model.model.ProductInfo;
import org.luke.model.model.TransactionInfo;
import org.luke.model.model.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    public TransactionInfo toInfo(Transaction transaction) {
        TransactionInfo info = new TransactionInfo();
        info.setTransId(transaction.getId());

        UserInfo sender = new UserInfo();
        sender.setUserId(transaction.getSender());
        info.setSender(sender);

        UserInfo receiver = new UserInfo();
        receiver.setUserId(transaction.getReceiver());
        info.setReceiver(receiver);

        ProductInfo product = new ProductInfo();
        product.setProductId(transaction.getProductId());
        info.setProduct(product);

        info.setPrice(String.valueOf(transaction.getPrice()));
        info.setGmtCreate(TimeUtil.formatTime(transaction.getGmtCreate()));
        info.setGmtModified(TimeUtil.formatTime(transaction.getGmtModified()));
        info.setQuantity(transaction.getQuantity());
        info.setTransStatus(transaction.getStatus());
        info.setTotal(String.valueOf(transaction.getTotal()));

        return info;
    }
}
