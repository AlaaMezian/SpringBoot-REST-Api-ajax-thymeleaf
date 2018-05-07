package com.waffa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CoreValidations {

	private final static Logger logger = LoggerFactory.getLogger(CoreValidations.class);

	static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";


	private static final String PASSWORD_PATTERN = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@%+'!#$^?:,/\\\\(){}\\[\\]~_-])[a-zA-Z\\d@%+'!#$^?:,/\\\\(){}\\[\\]~_-]{8,20})$";

	/*
	 * Method to validate email
	 */
	public static boolean isEmailValid(String email) {
		if (StringUtils.isEmpty(email)) {
			return false;
		} else {
			return email.matches(EMAIL_REGEX);

		}

	}

	public static boolean containNumber(String password) {
		return password.matches(".*\\d.*");
	}

	public static boolean containLowerCase(String password) {
		return password.matches(".*[a-z].*");
	}

	public static boolean containUpperCase(String password) {
		return password.matches(".*[A-Z].*");
	}

	public static boolean containSpecialCharacters(String password) {
		if (password.matches(".*[{}<>()`~!@#$%^&*-+=:;'\".,?\\/|].*"))
			return true;
		if (password.matches(".*\\[.*"))
			return true;
		if (password.matches(".*\\].*"))
			return true;
		if (password.matches(".*\\s.*"))
			return true;
		return false;
	}

	/*
	 * Method to validate mobile number based on min and max length
	 */
	public static boolean isNumberValid(String number, int minLength, int maxLength) {
		if (StringUtils.isEmpty(number)) {
			return false;
		} else {
			String regex = "\\d{" + minLength + "," + maxLength + "}";
			if (number.matches(regex)) {
				return true;
			} else {
				return false;
			}

		}
	}

	/*
	 * Method to validate mobile number based on min and max length
	 */
	public static boolean isNumber(String number, int minLength, int maxLength) {
		if (StringUtils.isEmpty(number)) {
			return false;
		} else {
			String regex = "\\d{" + minLength + "," + maxLength + "}";
			if (number.matches(regex)) {
				return true;
			} else {
				return false;
			}

		}
	}

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isAlphaNumeric(String value) {
		String PATTERN = "^[0-9a-zA-Z]+$";
		return value.matches(PATTERN);
	}

}
