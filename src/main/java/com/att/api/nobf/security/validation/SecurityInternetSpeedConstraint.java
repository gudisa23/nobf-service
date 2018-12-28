package com.att.api.nobf.security.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = SecurityInternetSpeedValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityInternetSpeedConstraint {
	String message() default "Unrecognized internet speed range provided.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
