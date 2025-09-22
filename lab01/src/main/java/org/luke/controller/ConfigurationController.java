package org.luke.controller;

import org.luke.model.req.ConfigurationChangeReq;
import org.luke.service.ConfigurationService;
import org.luke.web.annotations.ApiLab;
import org.luke.web.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@ApiLab(labName = "lab01", prefix = "configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configService;

    @PostMapping("/change")
    public BaseResponse changeConfiguration(@RequestBody ConfigurationChangeReq req) {
        return configService.changeConfiguration(req);
    }
}
