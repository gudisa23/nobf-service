package com.att.api.nobf.security.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.att.api.nobf.security.constants.SecurityConstants;

public class SecurityInternetSpeedValidator implements ConstraintValidator<SecurityInternetSpeedConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return SecurityConstants.INTERNET_SPEED_RANGES.contains(value);
	}

}
