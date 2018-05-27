package com.appcom.waffa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String msg;

	public NotFoundException() {
		super();
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	public NotFoundException(String exception) {
		super(exception);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
