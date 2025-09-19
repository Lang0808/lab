package org.luke.web.model;

import lombok.Getter;
import lombok.Setter;
import org.luke.common.model.exception.ErrorCode;

@Getter
@Setter
public class BaseResponse {
    private String labName;
    private String responseTime;
    private String errorCode;
    private String message;
}
