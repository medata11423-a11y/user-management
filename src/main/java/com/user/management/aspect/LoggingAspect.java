package com.user.management.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.demo.service.*.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("Aspect Method started: "
                + joinPoint.getSignature().getName());
    }

}
