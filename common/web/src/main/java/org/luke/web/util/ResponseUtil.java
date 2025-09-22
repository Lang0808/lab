package org.luke.web.util;

import org.luke.common.model.exception.ErrorCode;
import org.luke.common.util.TimeUtil;
import org.luke.web.annotations.ApiLab;
import org.luke.web.model.BaseResponse;

public class ResponseUtil {

    public static void setBaseResponseBasicInfos(BaseResponse baseResponse, ApiLab apiLab) {
        baseResponse.setErrorCode(ErrorCode.SUCCESS.getErrorCode());
        baseResponse.setMessage("API execute success");
        if (apiLab == null) return;
        baseResponse.setLabName(apiLab.labName());
        baseResponse.setResponseTime(TimeUtil.getAndFormatCurrentTime());
    }
}
