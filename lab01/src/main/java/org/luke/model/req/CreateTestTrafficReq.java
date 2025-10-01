package org.luke.model.req;

import lombok.Data;

@Data
public class CreateTestTrafficReq {

    /**
     * Total number of transactions to create
     */
    private int nTransTotal;
}
