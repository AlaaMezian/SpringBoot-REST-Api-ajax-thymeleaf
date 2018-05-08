package com.waffa.exceptions;

import java.text.MessageFormat;

import com.waffa.exception.code.CategoriesExceptionCode;

public class CategoriesException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String msg = null;

	private CategoriesExceptionCode categoriesExceptionCode = null;
	
	public CategoriesException() {
		super();
	}
	
	public CategoriesException(Throwable cause) {
		super(cause);
	}
	
	public CategoriesException(CategoriesExceptionCode code, String message) {
		super(MessageFormat.format(code.getErrMsg(), message));
		this.msg = message;
		this.categoriesExceptionCode = code;
	}
	
	@Override
	public String toString() {
		return MessageFormat.format(categoriesExceptionCode.getErrMsg(), msg);
	}
	
	public CategoriesExceptionCode getCategoriesCode() {
		return categoriesExceptionCode;
	}
	public void setCategoriesCode(CategoriesExceptionCode categoriesCode) {
		this.categoriesExceptionCode = categoriesCode;
	}
	
	public String getMess() {
		return msg;
    }
	
}
