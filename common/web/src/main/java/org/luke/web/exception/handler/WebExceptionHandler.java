package org.luke.web.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.luke.web.annotations.ApiLab;
import org.luke.web.exception.model.BaseException;
import org.luke.web.exception.model.ExceptionResponse;
import org.luke.web.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionResponse> handleException(BaseException e, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();

        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();

        // Try to get HandlerMethod
        Object handlerAttr = request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
        ApiLab apiLab = null;
        if (handlerAttr instanceof HandlerMethod handlerMethod) {
            Class<?> controllerClass = handlerMethod.getBeanType();

            apiLab = controllerClass.getAnnotation(ApiLab.class);
        }

        ResponseUtil.setBaseResponseBasicInfos(response, apiLab);
        response.setErrorCode(e.getErrorCode().name());
        response.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
