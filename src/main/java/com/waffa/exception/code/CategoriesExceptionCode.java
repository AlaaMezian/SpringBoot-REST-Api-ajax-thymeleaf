package com.waffa.exception.code;

public enum CategoriesExceptionCode {

	ERROR_RETRIVING_LIST("400", "cant fetch list");
    
	private final String errCode;
	private final String errMsg;
	
	private CategoriesExceptionCode(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}
	
	
}
