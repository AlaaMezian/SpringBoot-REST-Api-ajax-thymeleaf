package com.appcom.waffa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnAuthorizedException() {
		super();
	}

	public UnAuthorizedException(Throwable cause) {
		super(cause);
	}

	public UnAuthorizedException(String exception) {

		super(exception);
	}
}
