package com.infy.todo.utility;

import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	@AfterThrowing(pointcut = "execution(* com.infy.todo.service.TodoServiceImpl.*(..))", throwing ="ex")
	public void logExceptions(Exception ex) {
		LogFactory.getLog(getClass()).error(ex.getMessage(), ex);
	}

}
