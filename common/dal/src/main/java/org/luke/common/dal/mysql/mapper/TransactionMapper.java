package org.luke.common.dal.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.luke.common.dal.migrations.PerformDBMigration;
import org.luke.common.dal.model.Paging;
import org.luke.common.dal.model.Transaction;

import java.util.List;

@Mapper
@PerformDBMigration(
        newDBSqlSessionFactoryRef = "sqlSessionFactoryNewDB"
)
public interface TransactionMapper {
    /**
     * get a transaction by transaction id
     *
     * @param transId id of transaction
     * @return transaction information (if any). Otherwise, return null
     */
    Transaction selectTransaction(String transId);

    /**
     * insert transaction to database.
     *
     * @param transaction transaction information
     */
    void insertTransaction(Transaction transaction);

    /**
     * count number of row in transactions
     *
     * @return number of row
     */
    int countTransaction();

    /**
     * get transactions based on filter and paging
     *
     * @param filter filter
     * @param paging paging
     * @return transactions information that satisfies filter and within paging
     */
    List<Transaction> selectTransactions(Transaction filter, Paging paging);

    /**
     * count number of transactions that satisfies filter
     *
     * @param filter filter
     * @return number of transactions that satisfies filter
     */
    int countTransactions(Transaction filter);
}
