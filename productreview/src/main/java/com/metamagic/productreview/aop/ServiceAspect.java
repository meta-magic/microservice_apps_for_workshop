package com.metamagic.productreview.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

@Component
@Aspect
public class ServiceAspect {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServiceAspect.class);

	@Around("execution(* com.metamagic.productreview.service.*..*(..))")
	public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String msg = joinPoint.getTarget().getClass() + ", method =" + signature.getName();
		LOGGER.info(" Executing [ " + msg + "  ] STARTS");
		Object response = joinPoint.proceed();
		LOGGER.info(" Executing [ " + msg + "  ] ENDS");
		return response;
	}
}