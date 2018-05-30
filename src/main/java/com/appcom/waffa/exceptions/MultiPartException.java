package com.appcom.waffa.exceptions;

public class MultiPartException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MultiPartException() {
		super();
	}

	public MultiPartException(Throwable cause) {
		super(cause);
	}

	public MultiPartException(String exception) {
		super(exception);
	}

}
