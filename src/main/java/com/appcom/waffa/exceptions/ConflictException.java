package com.appcom.waffa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConflictException() {
		super();
	}

	public ConflictException(Throwable cause) {
		super(cause);
	}

	public ConflictException(String exception) {

		super(exception);
	}
}
