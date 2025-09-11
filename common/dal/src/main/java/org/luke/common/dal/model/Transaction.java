package org.luke.common.dal.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    private String id;
    private TransactionStatus status;
    private String sender;
    private String receiver;
    private String productId;
    private long price;
    private int quantity;
    private long total;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
