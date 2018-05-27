package com.appcom.waffa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InternalServerErrorException() {
		super();
	}

	public InternalServerErrorException(Throwable cause) {
		super(cause);
	}

	public InternalServerErrorException(String exception) {
		super(exception);
	}

}
