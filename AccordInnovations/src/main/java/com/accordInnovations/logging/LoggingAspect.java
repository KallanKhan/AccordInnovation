package com.accordInnovations.logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Pointcut("within(com.accordInnovations.controller..*)")
	public void controllerMethods() {
	}

	@Before("controllerMethods()")
	public void logBefore() {
		System.out.println("Request received");
	}

	@AfterReturning("controllerMethods()")
	public void logAfterReturning() {
		System.out.println("Response sent");
	}
}
