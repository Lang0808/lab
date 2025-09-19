package org.luke.model.model;

import lombok.Getter;
import lombok.Setter;
import org.luke.common.dal.model.TransactionStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionInfo {
    private String transId;
    private UserInfo sender;
    private UserInfo receiver;
    private TransactionStatus transStatus;
    private ProductInfo product;
    private String price;
    private int quantity;
    private String total;
    private String gmtCreate;
    private String gmtModified;
}
