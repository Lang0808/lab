package org.luke.controller;

import org.luke.generator.OldDBDataGenerator;
import org.luke.model.req.CountRowReq;
import org.luke.model.resp.CountRowResp;
import org.luke.service.OldDBService;
import org.luke.web.annotations.ApiLab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ApiLab(labName = "lab01", prefix = "old_db")
public class OldDBController {

    @Autowired
    private OldDBDataGenerator oldDBDataGenerator;

    @Autowired
    private OldDBService oldDBService;

    @PostMapping("/generate")
    public void generateOldDB() {
        oldDBDataGenerator.generateInitialDatabaseTest();
    }

    @GetMapping("/count")
    public CountRowResp countRowOldDB(CountRowReq req) {
        return oldDBService.countRowOldDB(req);
    }
}
