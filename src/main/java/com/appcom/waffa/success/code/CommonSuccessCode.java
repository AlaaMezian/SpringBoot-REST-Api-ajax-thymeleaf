package com.appcom.waffa.success.code;

import lombok.Getter;

@Getter
public enum CommonSuccessCode {

	Success("200","OK"), Created("201", "Created"), Accepted("202", "Accepted");

	private String value;
	private String msg;

	CommonSuccessCode(String value, String msg) {
		this.value = value;
		this.msg = msg; 
	}

	@Override
	public String toString() {
		return this.value;
	}
}
