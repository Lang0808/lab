package org.luke.common.dal.migrations;

import lombok.Data;
import org.luke.common.dal.migrations.enums.DBReadType;
import org.luke.common.dal.migrations.enums.DBWriteType;

@Data
public class DBMigrationInfo {
    private DBReadType readType;
    private DBWriteType writeType;
}
