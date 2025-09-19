package org.luke.model.resp;

import lombok.Getter;
import lombok.Setter;
import org.luke.model.model.TransactionInfo;
import org.luke.web.model.BaseResponse;

@Getter
@Setter
public class GetTransactionResp extends BaseResponse {
    private TransactionInfo transInfo;
}
