package org.luke.controller;

import org.luke.common.dal.model.Transaction;
import org.luke.common.model.exception.ErrorCode;
import org.luke.web.annotations.ApiLab;
import org.luke.web.exception.model.BaseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * this class contains APIs that allows stress-testing tool to test and verify result
 * to make sure that db migration process works well
 */
@Controller
@ApiLab(labName = "lab01", prefix = "api_test")
public class LoadTestApiController {

    @GetMapping("/transaction")
    public Transaction getTransaction(String id) {
        throw new BaseException(ErrorCode.UN_SUPPORTED, "get transaction is not supported yet");
    }
}
