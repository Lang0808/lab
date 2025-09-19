package org.luke.service.converter;

import org.luke.common.dal.model.Transaction;
import org.luke.model.model.TransactionInfo;
import org.luke.model.model.UserInfo;

public class TransactionConverter {

    public TransactionInfo toInfo(Transaction transaction) {
        TransactionInfo info = new TransactionInfo();
        info.setTransId(transaction.getId());

        UserInfo sender =
        info.setSender();
    }
}
