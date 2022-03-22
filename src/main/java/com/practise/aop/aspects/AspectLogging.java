package com.practise.aop.aspects;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AspectLogging {

	private Logger log = LoggerFactory.getLogger(AspectLogging.class);
	
	// ADVICES

	@Before(value="controllerLogging()")
	public void controllerLoggingAdvice(JoinPoint jp) {
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String requestUrl = request.getRequestURL().toString();
		
		log.info("Requested URL = {}", requestUrl);
		log.info("Entering the Method {} ", jp.getSignature().getName());
		log.info("method signature {}, {}", jp.getSignature(), Arrays.asList(jp.getArgs()));

	}
	
	@AfterReturning(value = "controllerLogging()", returning="value")
	public void controllerLoggingAdvice2(JoinPoint jp, Object value) {
		log.info("Sending {} from the controller method = {} ", value, jp.getSignature().getName());
	}

	@Around(value = "controllerLogging()")
	public ResponseEntity controllerLoggingAdvice3(ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		ResponseEntity rpe = null;
		try {
			rpe = (ResponseEntity) pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long total = System.currentTimeMillis() - start;
		log.info("Total time taken to serve the request in milliSec for the controller method: {} is = {} ", pjp.getSignature().getName(), total);	
		return rpe;
	}

	
	// POINTCUTS
	
	@Pointcut(value = "execution(* com.practise.aop.controllers.*.*(..))")
	public void controllerLogging() {}
	
}
