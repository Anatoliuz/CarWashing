package com.interview.carwash.error;

public class OperationNotFound extends RuntimeException {

    public OperationNotFound() {
        super("Invalid operation name");
    }

}
