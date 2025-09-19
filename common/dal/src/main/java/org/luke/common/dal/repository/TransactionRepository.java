package org.luke.common.dal.repository;

import org.luke.common.dal.model.Paging;
import org.luke.common.dal.model.Transaction;
import org.luke.common.dal.mysql.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionRepository {

    @Autowired
    private TransactionMapper mapper;

    public Transaction selectTransaction(String transId) {
        return mapper.selectTransaction(transId);
    }

    public void insertTransaction(Transaction transaction) {
        mapper.insertTransaction(transaction);
    }

    public int countTransaction() {
        return mapper.countTransaction();
    }

    public List<Transaction> selectTransactions(Transaction filter, Paging paging) {
        return mapper.selectTransactions(filter, paging);
    }

    public int countTransactions(Transaction filter) {
        return mapper.countTransactions(filter);
    }
}
