 package com.appcom.waffa.success.code;

public enum PasswordSuccessCode {

	PASSWORD_CHANGED_SUCCESSFULLY("200","password changed successfully");
	
	public String value;
	public String msg;
	
	PasswordSuccessCode(String value, String msg) {
		this.value = value;
		this.msg = msg;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
