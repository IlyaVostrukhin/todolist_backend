package com.projects.backend.todo.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Calendar;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    @Around("execution(* com.projects.backend.todo.controller..*(..)))")
    public Object profileControllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        log.info("-----    Executing " + className + "." + methodName + ": " + Calendar.getInstance().getTime() + "   -----");

        StopWatch countdown = new StopWatch();
        countdown.start();
        Object result = proceedingJoinPoint.proceed();
        countdown.stop();

        log.info("-----    Execution time of " + className + "." + methodName + ": " + countdown.getTotalTimeMillis() + "ms   -----");

        return result;
    }

}
