package com.interview.carwash.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Aspect injects log error on controllers' post methods
 * asking user about dto check
 */
@Slf4j
@Aspect
@Component
public class TryCatchAspect {

    @AfterThrowing(pointcut =
            "execution(* com.interview.carwash.controller.UiController.post(..)) || " +
            "execution(* com.interview.carwash.controller.OperationController.price(..))" +
            "execution(* com.interview.carwash.controller.OperationController.post(..))" +
            "execution(* com.interview.carwash.controller.WashingController.post(..))",
            throwing = "ex")
    public void dtoEx(JoinPoint joinPoint, Throwable ex) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String stuff = signature.toString();
        String arguments = Arrays.toString(joinPoint.getArgs());
        log.error("Please check dto params... We have caught exception in method: "
                + methodName + " with arguments "
                + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
                + ex.getMessage());
    }

}
