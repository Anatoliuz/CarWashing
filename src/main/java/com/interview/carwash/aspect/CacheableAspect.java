package com.interview.carwash.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Cacheable impl: method annotation
 * which saves values in cache to speed up work.
 * Cache life longevity is not managed.
 */
@Slf4j
@Aspect
@Component
public class CacheableAspect {

    @Data
    @AllArgsConstructor
    class MethodCall {
        private String className;
        private String methodName;
        private Object[] args;
    }

    private Map<MethodCall, Object> cache = new HashMap<>();

    @Pointcut("@annotation(Cacheable)")
    public void cacheableMethod() {
    }

    @Around("cacheableMethod()")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        MethodCall methodCall = new MethodCall(className, methodName, joinPoint.getArgs());
        Object mbResult = cache.get(methodCall);
        if (mbResult != null) {
            log.info("Received value from cache");
            return mbResult;
        }

        Object result = joinPoint.proceed();
        cache.put(methodCall, result);
        return result;
    }
}
