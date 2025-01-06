package com.accordInnovations.logging;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import jakarta.servlet.http.HttpServletRequest;


@Aspect
@Component
public class LoggingAspect {

    private final HttpServletRequest httpServletRequest;

    public LoggingAspect(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Pointcut("within(com.accordInnovations.controller..*)")
    public void controllerMethods() {
    }

    @Before("controllerMethods()")
    public void logBefore() {
        if (httpServletRequest instanceof ContentCachingRequestWrapper) {
            ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) httpServletRequest;

            String body = new String(cachingRequest.getContentAsByteArray());
            System.out.println("Request Body: " + body);
        } else {
            System.out.println("Unable to capture the request body. Ensure ContentCachingRequestWrapper is used.");
        }
    }

    @AfterReturning(pointcut = "controllerMethods()", returning = "responseBody")
    public void logAfterReturning(Object responseBody) {
        System.out.println("Response Body: " + responseBody);
        System.out.println("Response sent");
    }
}
