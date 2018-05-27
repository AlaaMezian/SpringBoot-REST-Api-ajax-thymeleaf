package com.appcom.waffa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class FailedException extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	
	public FailedException() {
		super();
	}

	public FailedException(Throwable cause) {
		super(cause);
	}

	public FailedException(String exception) {

		super(exception);
	}
}
