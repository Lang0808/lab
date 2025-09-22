package org.luke.service;

import org.luke.common.dal.migrations.DBMigrationInfo;
import org.luke.common.dal.migrations.enums.DBReadType;
import org.luke.common.dal.migrations.enums.DBWriteType;
import org.luke.common.model.exception.ErrorCode;
import org.luke.model.enumerate.ConfigType;
import org.luke.model.req.ConfigurationChangeReq;
import org.luke.web.exception.model.BaseException;
import org.luke.web.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    @Autowired
    private volatile DBMigrationInfo migrationInfo;

    public BaseResponse changeConfiguration(ConfigurationChangeReq req) {
        if (req.getConfigType().equals(ConfigType.READ)) {
            migrationInfo.setReadType(DBReadType.valueOf(req.getValue()));
        } else if (req.getConfigType().equals(ConfigType.WRITE)) {
            migrationInfo.setWriteType(DBWriteType.valueOf(req.getValue()));
        } else throw new BaseException(ErrorCode.INVALID_PARAMS, "Invalid config type");
        return new BaseResponse();
    }
}
