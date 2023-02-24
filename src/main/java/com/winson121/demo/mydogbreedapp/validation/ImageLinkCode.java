package com.winson121.demo.mydogbreedapp.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ImageLinkConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageLinkCode {
	
	// define default image link code
	public String value() default "https://images.dog.ceo/breeds/";
	
	// define default error message
	public String message() default "must start with https://images.dog.ceo/breeds/ and ends with .jpg";
	
	// define default groups
	public Class<?>[] groups() default {};
	
	// define default payloads
	public Class<? extends Payload>[] payload() default {};
	
}
