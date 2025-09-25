package org.luke.common.dal.model;

import lombok.Data;

import java.util.Date;

@Data
public class Merchant {
    private String id;
    private String status;
    private String name;
    private String description;
    private Date gmtCreate;
    private Date gmtModified;
}
