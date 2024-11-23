package ru.promo.consul_plan.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("within(ru.promo.consul_plan.controller..*)")
    public void controllerMethods() {}

    @Before("controllerMethods()")
    public void logRequest(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String headers = request.getHeaderNames().toString();

        log.info("Request: {} {}?{} Headers: {}", method, uri, queryString, headers);
        log.info("Executing method: {}", joinPoint.getSignature().toShortString());
    }
}