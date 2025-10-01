package org.luke.controller;

import org.luke.common.model.exception.ErrorCode;
import org.luke.generator.GenTaskCreateTrans;
import org.luke.generator.GenerateDBTask;
import org.luke.generator.OldDBDataGenerator;
import org.luke.generator.config.OldDBDataGeneratorConfig;
import org.luke.model.req.CountRowReq;
import org.luke.model.req.CreateTestTrafficReq;
import org.luke.model.resp.CountRowResp;
import org.luke.model.resp.GenerateOldDataResp;
import org.luke.service.OldDBService;
import org.luke.web.annotations.ApiLab;
import org.luke.web.exception.model.BaseException;
import org.luke.web.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class contains API for generating and working with old database
 */
@Controller
@ApiLab(labName = "lab01", prefix = "old_db")
public class OldDBController {

    @Autowired
    private OldDBDataGenerator oldDBDataGenerator;

    @Autowired
    private OldDBDataGeneratorConfig oldDBDataGeneratorConfig;

    @Autowired
    private OldDBService oldDBService;

    @Autowired
    @Qualifier("generatorExecutor")
    private Executor executor;

    @PostMapping("/generate")
    public GenerateOldDataResp generateOldDB() {
        if (!OldDBDataGenerator.tryRun()) {
            throw new BaseException(ErrorCode.RATE_LIMITED, "Only 1 task generator can run at 1 time");
        }

        AtomicInteger current = new AtomicInteger(0);
        for (int i = 0; i < oldDBDataGeneratorConfig.getConcurrency(); ++i) {
            executor.execute(new GenerateDBTask(
                    current.getAndIncrement(),
                    oldDBDataGeneratorConfig.getTransaction(),
                    oldDBDataGenerator,
                    current,
                    executor
            ));
        }

        return new GenerateOldDataResp();
    }

    @GetMapping("/count")
    public CountRowResp countRowOldDB(CountRowReq req) {
        return oldDBService.countRowOldDB(req);
    }

    @PostMapping("/create_test_traffic")
    public BaseResponse createTestTraffic(@RequestBody CreateTestTrafficReq req) {
        GenTaskCreateTrans task = new GenTaskCreateTrans(
                req.getNTransTotal(),
                0,
                1000,
                executor,
                oldDBDataGenerator);
        executor.execute(task);
        return new BaseResponse();
    }
}
