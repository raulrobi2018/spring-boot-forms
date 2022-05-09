package com.rr.springbootweb.forms.app.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class RequiredValidation implements ConstraintValidator<Required, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || !StringUtils.hasText(value))
			return false;
		return true;
	}

}
