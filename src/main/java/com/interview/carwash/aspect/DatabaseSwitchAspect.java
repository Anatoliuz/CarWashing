//package com.interview.carwash.aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.data.repository.Repository;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Aspect
//@Component
//public class DatabaseSwitchAspect {
//
//    private static final ThreadLocal<String> targetDatabase = new ThreadLocal<>();
//
//    public static void setTargetDatabase(String database) {
//        targetDatabase.set(database);
//    }
//
//    public static void clearTargetDatabase() {
//        targetDatabase.remove();
//    }
//
//    @Before("execution(* org.springframework.data.repository.Repository+.*(..))")
//    public void switchDatabase(JoinPoint joinPoint) {
//        String database = targetDatabase.get();
//        if (database != null && joinPoint.getTarget() instanceof Repository) {
//            Repository repository = (Repository) joinPoint.getTarget();
//            repository.setDatabase(database);
//        }
//    }
//}
