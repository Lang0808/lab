package org.luke.model.req;

import lombok.Data;
import org.luke.model.enumerate.ConfigType;

@Data
public class ConfigurationChangeReq {
    private ConfigType configType;
    private String value;
}
