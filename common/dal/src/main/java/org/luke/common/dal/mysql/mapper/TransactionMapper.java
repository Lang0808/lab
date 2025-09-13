package org.luke.common.dal.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.luke.common.dal.model.Transaction;

@Mapper
public interface TransactionMapper {
    /**
     * get a transaction by transaction id
     * @param transId id of transaction
     * @return transaction information (if any). Otherwise, return null
     */
    Transaction selectTransaction(String transId);

    /**
     * insert transaction to database.
     * @param transaction transaction information
     */
    void insertTransaction(Transaction transaction);

    /**
     * count number of row in transactions
     * @return number of row
     */
    int countTransaction();
}
