package org.luke.controller;

import org.luke.model.resp.GetTransactionResp;
import org.luke.service.LoadTestApiService;
import org.luke.web.annotations.ApiLab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * this class contains APIs that allows stress-testing tool to test and verify result
 * to make sure that db migration process works well
 */
@Controller
@ApiLab(labName = "lab01", prefix = "api_test")
public class LoadTestApiController {

    @Autowired
    private LoadTestApiService loadTestApiService;

    @GetMapping("/transaction")
    public GetTransactionResp getTransaction(String transId) {
        return loadTestApiService.getTransaction(transId);
    }
}
