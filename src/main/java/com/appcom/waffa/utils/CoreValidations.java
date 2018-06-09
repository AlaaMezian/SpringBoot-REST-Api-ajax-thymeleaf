package com.appcom.waffa.utils;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.appcom.waffa.exceptions.BadRequestException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class CoreValidations {

	static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

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
	//validate start date is before end date 
	public boolean validateDate(Date startDate, Date endDate) {
		boolean before = endDate.before(startDate);
		if (before) {
			return false;
		}
		return true;
	}
	
   //arabic validation 
	public static boolean validArabic(String s) {
        for (int i = 0; i < s.length(); ) {
            int c = s.codePointAt(i);
            if (c >= 0x0600 && c <= 0x06E0)
                return true;
            i += Character.charCount(c);
        }
        return false;
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

	public static void validatePhoneNumber(String phoneNumber) {

		if (phoneNumber.startsWith("0") || !phoneNumber.startsWith("+"))
			throw new BadRequestException("the phone number is not valid");

		boolean numeric = isNumeric(phoneNumber);
		if (!numeric || phoneNumber.contains(".") || phoneNumber.contains(","))
			throw new BadRequestException("the phone number is not valid");

		PhoneNumber phone = null;
		boolean isValidNumber = false;
		boolean isSANumber = false;

		PhoneNumberUtil pnUtil = PhoneNumberUtil.getInstance();
		try {
			
			phone = pnUtil.parse(phoneNumber, "SA");
			isValidNumber = pnUtil.isValidNumber(phone);
			isSANumber = pnUtil.getRegionCodeForNumber(phone).equals("SA");
		} catch (Exception e) {
			throw new BadRequestException("the phone code is not valid");
		}

		if (!isSANumber || !isValidNumber)
			throw new BadRequestException("the phone number is not valid");
		else {

			if (  phoneNumber.length() != 13 )
				throw new BadRequestException("the phone number length is invalid");
		}
	}

	// only contains number
	public static boolean isNumeric(String s) {
		return s != null && s.matches("[-+]?\\d*\\.?\\d+");
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
