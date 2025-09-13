package org.luke.web.configurer;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.luke.common.util.TimeUtil;
import org.luke.web.annotations.ApiLab;
import org.luke.web.model.BaseResponse;
import org.luke.web.util.ResponseUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public class BaseResponseInterceptor implements HandlerMethodReturnValueHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        log.info("support return type");
        Class<?> controllerClass = returnType.getContainingClass();
        return controllerClass.isAnnotationPresent(ApiLab.class);
    }

    @Override
    public void handleReturnValue(Object returnValue,
                                  MethodParameter returnType,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {

        log.info("handle return value");

        // Mark the request as handled to prevent default handling
        mavContainer.setRequestHandled(true);

        // Get the lab name from the controller class annotation
        Class<?> controllerClass = returnType.getContainingClass();
        ApiLab apiLab = controllerClass.getAnnotation(ApiLab.class);

        if (apiLab != null && returnValue instanceof BaseResponse) {
            log.info("base response and return value satisfy condition");
            ResponseUtil.setBaseResponseBasicInfos((BaseResponse) returnValue, apiLab);
        }

        // Write the modified object to the response
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        if (response != null) {
            log.info("write response");
            response.setContentType("application/json");
            objectMapper.writeValue(response.getWriter(), returnValue);
        }
    }
}

