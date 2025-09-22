package org.luke.model.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyProductReq {
    private String transId;
    private String productId;
    private String price;
    private Integer quantity;
    private String userId;
}
