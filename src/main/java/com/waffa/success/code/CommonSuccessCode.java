package com.waffa.success.code;

import lombok.Getter;

@Getter
public enum CommonSuccessCode {

	Success("200"),
	Created("201"),
	Accepted("202");
	
	private String value;

	CommonSuccessCode(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
