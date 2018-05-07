package com.waffa.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class MobileNumberValidator implements ConstraintValidator<MobileNumber, String> {

	@Override
	public void initialize(MobileNumber constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		try {
			PhoneNumber numberProto = phoneUtil.parse(value, "");
			boolean isValid = phoneUtil.isValidNumber(numberProto);
			if (isValid) {
				return true;
			} else {
				return false;
			}

		} catch (NumberParseException e) {
			return false;
		}
	}

}
