package com.appcom.waffa.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Constraint(validatedBy = MobileNumberValidator.class)
public @interface MobileNumber {
	String message() default "{Invalid Mobile Number please enter a valid one }";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
