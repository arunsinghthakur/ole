package com.ole.aspect;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger log = Logger.getLogger(LoggingAspect.class);

	@Around("execution(* com.ole.*.*.*())")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("The method " + joinPoint.getSignature().getName()
				+ "() begins with " + Arrays.toString(joinPoint.getArgs()));
		try {
			Object result = joinPoint.proceed();
			log.info("The method " + joinPoint.getSignature().getName()
					+ "() ends with " + result);
			return result;
		} catch (Exception e) {
			log.error("Illegal argument "
					+ Arrays.toString(joinPoint.getArgs()) + " in "
					+ joinPoint.getSignature().getName() + "()."
					+ "Reason : " + e);
			throw e;
		}
	}
}
