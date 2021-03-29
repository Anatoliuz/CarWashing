package com.interview.carwash.validation;

import com.interview.carwash.error.OperationNotFound;
import com.interview.carwash.service.OperationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@AllArgsConstructor
public class OperationValidator implements ConstraintValidator<OperationConstraint, String> {

    private final OperationService service;

    @Override
    public void initialize(OperationConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String operationName, ConstraintValidatorContext cxt) {
        try {
            return service.getByName(operationName) != null;
        } catch (OperationNotFound e) {
            log.warn(e.getMessage());
            return false;
        }
    }

}
