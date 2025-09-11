package org.luke.controller;

import org.luke.generator.OldDBDataGenerator;
import org.luke.web.annotations.ApiLab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ApiLab(labName = "lab01")
public class OldDBController {

    @Autowired
    private OldDBDataGenerator oldDBDataGenerator;

    @PostMapping("/generate_old_db")
    public void generateOldDB() {
        oldDBDataGenerator.generateInitialDatabaseTest();
    }
}
