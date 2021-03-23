package com.interview.carwash.error;

public class WashingNotFound extends RuntimeException {

    public WashingNotFound() {
        super("Washing not found");
    }

}
