package com.waffa.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class ChangePasswordModel {

	@NotNull
	@NotBlank(message = "Please enter the old password")
	private String oldPassword;
	
	@NotNull
	@NotBlank(message = "Please enter the new password")
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	

}