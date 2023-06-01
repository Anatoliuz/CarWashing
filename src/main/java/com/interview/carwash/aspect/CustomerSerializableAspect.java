package com.interview.carwash.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.carwash.model.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @CustomerSerializable method annotation realization
 * to convert Map<String, Object> to Json Class
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class CustomerSerializableAspect {

    private ObjectMapper objectMapper;

    @Pointcut("@annotation(CustomerSerializable)")
    public void customerSerializable() {
    }


    @Around("customerSerializable()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Customer customer = objectMapper.convertValue(joinPoint.getArgs()[0], Customer.class);
            return joinPoint.proceed(Collections.singletonList(customer).toArray());
        } catch (Throwable throwable) {
            return joinPoint.proceed(joinPoint.getArgs());
        }
    }

}
