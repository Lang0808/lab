package org.luke.model.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.luke.model.enumerate.DBTable;

@Data
public class CountRowReq {

    @NotNull
    private DBTable tableName;
}
