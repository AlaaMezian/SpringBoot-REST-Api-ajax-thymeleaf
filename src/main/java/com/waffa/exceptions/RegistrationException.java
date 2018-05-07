package com.waffa.exceptions;

import java.text.MessageFormat;

import com.waffa.exception.code.RegistrationExceptionCode;

public class RegistrationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String msg = null;

	private RegistrationExceptionCode registrationCode = null;
	
	public RegistrationException() {
		super();
	}

	public RegistrationException(Throwable cause) {
		super(cause);
	}

	public RegistrationException(RegistrationExceptionCode code, String message) {
		super(MessageFormat.format(code.getErrMsg(), message));
		this.msg = message;
		this.registrationCode = code;
	}
	
	@Override
	public String toString() {
		return MessageFormat.format(registrationCode.getErrMsg(), msg);
	}

	public RegistrationExceptionCode getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(RegistrationExceptionCode registrationCode) {
		this.registrationCode = registrationCode;
	}

	public String getMess() {
		return msg;
    }
}
