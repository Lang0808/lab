package org.luke.service;

import org.luke.common.dal.repository.TransactionRepository;
import org.luke.common.model.exception.ErrorCode;
import org.luke.model.enumerate.DBTable;
import org.luke.model.req.CountRowReq;
import org.luke.model.resp.CountRowResp;
import org.luke.web.exception.model.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
public class OldDBService {

    @Autowired
    private TransactionRepository transRepo;

    public CountRowResp countRowOldDB(@Validated CountRowReq req) {
        int nRow = 0;
        if(req.getTableName().equals(DBTable.TRANSACTION)) {
            nRow = transRepo.countTransaction();
        }
        else {
            throw new BaseException(ErrorCode.UN_SUPPORTED, "Count API doesn't support this table");
        }

        CountRowResp resp = new CountRowResp();
        resp.setNRow(nRow);
        return resp;
    }

}
