package org.luke.common.dal.model;

import lombok.Data;

@Data
public class Paging {
    private int offset;
    private int limit;
}
