package org.luke.model.req;

import lombok.Data;
import org.luke.common.dal.model.TransactionStatus;

@Data
public class CreateTransactionReq {
    private String transId;
    private TransactionStatus status;
}
