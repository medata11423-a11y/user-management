package com.user.management.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.user.management.service.*.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("Aspect Method started: {}", joinPoint.getSignature().getName());
    }


    @AfterReturning("execution(* com.user.management.service.*.*(..))")
    public void after(JoinPoint joinPoint) {
        log.info("Aspect Method completed: {}", joinPoint.getSignature().getName());
    }

}
