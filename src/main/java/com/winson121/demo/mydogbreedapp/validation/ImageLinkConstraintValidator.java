package com.winson121.demo.mydogbreedapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageLinkConstraintValidator
		implements ConstraintValidator<ImageLinkCode, String> {
	
	private String imageLinkPrefix;
	
	@Override
	public void initialize(ImageLinkCode theImageLinkCode) {
		imageLinkPrefix = theImageLinkCode.value();
	}

	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {
	
		boolean result;
		
		if (theCode != null) {
			Pattern pattern = Pattern.compile("https://images.dog.ceo/breeds/[a-z]+[-][a-z]+/[a-z0-9]+.jpg");
			Matcher m = pattern.matcher(theCode);
			result = m.matches();
		}
		else {
			result = true;
		}

		return result;
	}

}
