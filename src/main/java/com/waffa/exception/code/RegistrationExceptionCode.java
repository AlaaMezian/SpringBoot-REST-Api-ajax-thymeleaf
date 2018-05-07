package com.waffa.exception.code;

public enum RegistrationExceptionCode {

	ALL_FIELDS_MANDATORY("400", "All fields are mandatory."), 
    USER_NAME_NOT_VALID("400","Allowed characters: A-Z a-z 0-9"),
    INVALID_EMAIL_EXCEPTION("400","Invalid Email Address"),
    EMAIL_ALREADY_REGISTRED("409","The Email you are trying to register already exist"),
    SIGNUP_EXCEPTION("404","SignUp Failed Please try again later");


	
	private final String errCode;
	private final String errMsg;
	
	private RegistrationExceptionCode(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	/**
	 * Return a string representation of this status code.
	 */
	@Override
	public String toString() {
		return errCode + ": " + errMsg;
	}
	
	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}
}
