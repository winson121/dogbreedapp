package com.winson121.demo.mydogbreedapp.exception;

public class BreedNotFoundException extends RuntimeException {
	public BreedNotFoundException(String message) {
        super(message);
    }

    public BreedNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BreedNotFoundException(Throwable cause) {
        super(cause);
    }
}
