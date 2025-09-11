package org.luke.web.configurer;

import lombok.extern.slf4j.Slf4j;
import org.luke.web.annotations.ApiLab;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Slf4j
@Component
public class ApiLabHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    @Nullable
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        log.info("method = {}", method);
        log.info("handlerType = {}", handlerType);
        RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
        log.info("info = {}", info);
        if(handlerType.isAnnotationPresent(ApiLab.class)) {
            ApiLab apiLab = handlerType.getAnnotation(ApiLab.class);
            String basePath = String.format("%s/%s", apiLab.version(), apiLab.labName());
            if(info != null) {
                RequestMappingInfo newInfo = RequestMappingInfo.paths(basePath).build();
                return newInfo.combine(info);
            }
        }
        return info;
    }
}
