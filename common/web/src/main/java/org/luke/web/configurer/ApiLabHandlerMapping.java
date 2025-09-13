package org.luke.web.configurer;

import lombok.extern.slf4j.Slf4j;
import org.luke.web.annotations.ApiLab;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.function.support.RouterFunctionMapping;
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
            String basePath = String.format("%s/%s/%s", apiLab.version(), apiLab.labName(), apiLab.prefix());
            log.info("base path = {}", basePath);
            if(info != null) {
                RequestMappingInfo newInfo = RequestMappingInfo.paths(basePath).build();
                log.info("new info = {}", newInfo);
                RequestMappingInfo mappingInfo = newInfo.combine(info);
                log.info("mapping info = {}", mappingInfo);
                return mappingInfo;
            }
        }
        return info;
    }

    @Override
    protected void initInterceptors() {
        log.info("init interceptors");
        super.initInterceptors();
    }

    /**
     * Each HandlerMapping has a priority. If priority is lower than priority of SimpleUrlHandlerMapping, this HandlerMapping is never called.
     * @see DispatcherServlet#initHandlerMappings(ApplicationContext) to see process of sorting HandlerMapping by priority
     * Override getOrder returns highest precendence to make sure ApiLabHandlerMapping is used before every other HandlerMappings when finding HandlerAdapter for HTTP request
     *
     * @return highest precedence
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
