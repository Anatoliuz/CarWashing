package com.interview.carwash.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE_USE;

@Documented
@Constraint(validatedBy = OperationValidator.class)
@Target({ElementType.METHOD, FIELD, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationConstraint {

    String message() default "Invalid operation name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

