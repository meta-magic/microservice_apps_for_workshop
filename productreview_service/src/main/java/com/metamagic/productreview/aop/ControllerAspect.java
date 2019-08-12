package com.metamagic.productreview.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

@Component
@Aspect
@Order(1)
public class ControllerAspect {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ControllerAspect.class);

	@Around("execution(* com.metamagic.productreview.controller.*..*(..))")
	public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String msg = joinPoint.getTarget().getClass() + ", method =" + signature.getName();
		LOGGER.info(" Executing [ " + msg + "  ] STARTS");
		Object response = joinPoint.proceed();
		LOGGER.info(" Executing [ " + msg + "  ] ENDS");
		return response;
	}

}