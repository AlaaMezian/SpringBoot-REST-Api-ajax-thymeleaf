package com.waffa.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.waffa.utils.MobileNumber;

		
@Validated
public class RegistrationModel {
  

	@NotNull
	@NotBlank(message = "please enter a user name")
	@Size(max = 50, message = "Maximum number of characters is 50")
	private String userName;

	@NotNull
	@NotBlank(message = "please enter an email address")
	@Email(message = "you entered invalid email, please rewrite your email")
	private String userEmail;
	
	@NotNull
	@NotBlank(message = "please enter a mobile phone number")
	private String mobileNumber;
	
	@NotNull
	@NotBlank(message = "please enter passsword")
	private String password; 
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	
	
	
	
}
