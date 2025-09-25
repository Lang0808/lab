package org.luke.common.dal.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private String status;
    private String name;
    private Date gmtCreate;
    private Date gmtModified;
    private Long balance;
}
