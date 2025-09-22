package org.luke.model.resp;

import lombok.Getter;
import lombok.Setter;
import org.luke.web.model.BaseResponse;

@Getter
@Setter
public class BuyProductResp extends BaseResponse {
    private String transId;
    private String transStatus;
    private String total;
}
