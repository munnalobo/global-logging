package com.neuron.openai;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Aspect
@Component
@Slf4j
public class MyAspect {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Before("(execution(private * * (..)) || execution(* * (..)))")
    public void logParentAndCurrentMethodInfo(JoinPoint joinPoint) {
        String parentClassName = joinPoint.getSignature().getDeclaringType().getCanonicalName();
        String parentMethodName = joinPoint.getSignature().getName();
        String currentClassName = joinPoint.getTarget().getClass().getCanonicalName();
        LocalDateTime currentTime = LocalDateTime.now();
        String currentTimeString = formatter.format(currentTime);

        log.info("parent-class: {} | parent-method: {} | current-class: {} | currentTime: {}", parentClassName, parentMethodName, currentClassName, currentTimeString);
    }
}

