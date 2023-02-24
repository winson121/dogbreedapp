package com.winson121.springboot.demo.dogbreedapp.exception;

public class BreedNotFoundException extends RuntimeException{

	public BreedNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BreedNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BreedNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
