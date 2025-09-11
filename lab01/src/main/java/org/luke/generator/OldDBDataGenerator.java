package org.luke.generator;

import lombok.extern.slf4j.Slf4j;
import org.luke.common.dal.model.Transaction;
import org.luke.common.dal.model.TransactionStatus;
import org.luke.common.dal.repository.TransactionRepository;
import org.luke.common.util.IdUtil;
import org.luke.common.util.ProgressBarUtil;
import org.luke.common.util.RandomUtil;
import org.luke.generator.config.OldDBDataGeneratorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OldDBDataGenerator {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OldDBDataGeneratorConfig genConfig;

    public Transaction generateDataTransaction(int i) {
        Transaction transaction = new Transaction();
        transaction.setId(IdUtil.generateTransId(i));
        transaction.setPrice(Math.abs(RandomUtil.generateRandomLong()));
        transaction.setQuantity(Math.abs(RandomUtil.generateRandomInt()));
        transaction.setTotal(transaction.getPrice() * transaction.getQuantity());
        transaction.setSender(IdUtil.generateUserId(genConfig.getUser()));
        transaction.setReceiver(IdUtil.generateUserId(genConfig.getUser()));
        transaction.setProductId(IdUtil.generateProductId(genConfig.getProduct()));
        transaction.setStatus(RandomUtil.getRandomEnum(TransactionStatus.class));
        return transaction;
    }

    public void generateTransactionTable() {
        int curPercent = 1;
        for (int i=0; i<genConfig.getTransaction(); ++i) {
            Transaction transaction = generateDataTransaction(i);
            transactionRepository.insertTransaction(transaction);
            // process total i + 1 row
            // we want next percent --> i + 1 + 1
            int nextPercent = ProgressBarUtil.getPercentageProgress(i + 2, genConfig.getTransaction());
            if(curPercent != nextPercent) {
                log.info("generate transaction table ${}%", curPercent);
                curPercent += 1;
            }
        }
    }

    public void generateInitialDatabaseTest() {
        generateTransactionTable();
    }
}
