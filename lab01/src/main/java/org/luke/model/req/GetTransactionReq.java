package org.luke.model.req;

import lombok.Getter;
import lombok.Setter;
import org.luke.model.model.TransactionInfo;

@Getter
@Setter
public class GetTransactionReq {
    private String transId;
}
