package com.interview.carwash.error;

public class InvalidOperationName extends RuntimeException {

    public InvalidOperationName() {
        super("Invalid operation name");
    }

}
