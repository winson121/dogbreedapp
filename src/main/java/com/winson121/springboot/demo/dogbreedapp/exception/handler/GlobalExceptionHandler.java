package com.winson121.springboot.demo.dogbreedapp.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.winson121.springboot.demo.dogbreedapp.exception.BreedNotFoundException;
import com.winson121.springboot.demo.dogbreedapp.exception.GlobalErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// Add an exception handler for ToDoNotFoundException
	
		@ExceptionHandler
		public ResponseEntity<GlobalErrorResponse> handleException(BreedNotFoundException exc) {
			
			// create ToDoErrorResponse
			GlobalErrorResponse error = new GlobalErrorResponse(
										HttpStatus.NOT_FOUND.value(),
										exc.getMessage(),
										System.currentTimeMillis()
										);
			
			// return ResponseEntity
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		
		// Add another exception handler to catch any exception
		
		@ExceptionHandler
		public ResponseEntity<GlobalErrorResponse> handleException(Exception exc) {
			
			// create ToDoErrorResponse
			GlobalErrorResponse error = new GlobalErrorResponse(
										HttpStatus.BAD_REQUEST.value(),
										exc.getMessage(),
										System.currentTimeMillis());
			// return ResponseEntity
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
}
