package com.waffa.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

@Validated
public class LoginModel {

	@NotNull(message = "Username should be sent")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Should contain Alphanumeric only")
	private String username;
	
	@NotNull(message = "Please Enter Password")
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
