package com.cg.onlinebank.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.cg.onlinebank.exception.OnlineBankException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Aspect
@Configuration
public class LoggingAspect {
	
	private final Logger slf4jLogger = LoggerFactory.getLogger(this.getClass());
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Before("execution(* com.cg.onlinebank.service.CustomerServiceImpl..*(..))")
	public void customerLogging(JoinPoint joint) throws OnlineBankException {
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			slf4jLogger.info("Begin of - " + joint.getStaticPart().getSignature().getName() + " method");
			slf4jLogger.info("Info Input Parameters -:\n "
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(joint.getArgs()));
		} catch (JsonProcessingException e) {
			throw new OnlineBankException(e.getMessage());
		}
	}
	
	@AfterReturning(pointcut = "execution(*  com.cg.onlinebank.service.CustomerServiceImpl..*(..))", returning = "result")
	public void customerSetterMethodLogging(JoinPoint joint, Object result) throws OnlineBankException {
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {

			slf4jLogger.info("Info Output Parameters -: \n "
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(null != result ? result : ""));
			slf4jLogger.debug("end of  - " + joint.getStaticPart().getSignature().getName() + " method");
		} catch (JsonProcessingException e) {
			throw new OnlineBankException(e.getMessage());
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	
	
	@Before("execution(* com.cg.onlinebank.service.AdminServiceImpl..*(..))")
	public void adminLogging(JoinPoint joint) throws OnlineBankException {
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			slf4jLogger.info("Begin of - " + joint.getStaticPart().getSignature().getName() + " method");
			slf4jLogger.info("Info Input Parameters -:\n "
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(joint.getArgs()));
		} catch (JsonProcessingException e) {
			throw new OnlineBankException(e.getMessage());
		}
	}
	
	@AfterReturning(pointcut = "execution(*  com.cg.onlinebank.service.AdminServiceImpl..*(..))", returning = "result")
	public void adminSetterMethodLoggin(JoinPoint joint, Object result) throws OnlineBankException {
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {

			slf4jLogger.info("Info Output Parameters -: \n "
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(null != result ? result : ""));
			slf4jLogger.debug("end of  - " + joint.getStaticPart().getSignature().getName() + " method");
		} catch (JsonProcessingException e) {
			throw new OnlineBankException(e.getMessage());
		}
	}
	
	

}
