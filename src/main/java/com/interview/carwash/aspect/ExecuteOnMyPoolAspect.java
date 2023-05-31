package com.interview.carwash.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;


@Slf4j
@Aspect
@Component
public class ExecuteOnMyPoolAspect {

    private ExecutorService executor = new ForkJoinPool(16);

    @Pointcut("@annotation(ExecuteOnMyPool)")
    public void executeOnMyPool() {
    }

    @Pointcut("execution(void *.*(..))")
    public void voidMethod() {
    }

    @Around("executeOnMyPool()")
    public Object execute(ProceedingJoinPoint joinPoint) {
        executor.submit(() -> {
            try {
                joinPoint.proceed();
                log.info("Executing in my custom thread");
            } catch (Throwable throwable) {
            }
        });
        return null;
    }

}
