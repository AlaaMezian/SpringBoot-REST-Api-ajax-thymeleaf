package com.appcom.waffa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.appcom.waffa.constant.Status;


@Entity
@Table(name = "user")
public class User extends BaseEntity implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	

	@Column(name = "user_name")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "is_active")
	@Enumerated(EnumType.STRING)
	private Status isActive;
	
	@Column(name = "mobile_number",unique = true)
	private String mobileNumber;
	
	@OneToOne(cascade =CascadeType.MERGE)
	private Token tokenFCM;


	public Token getTokenFCM() {
		return tokenFCM;
	}

	public void setTokenFCM(Token tokenFCM) {
		this.tokenFCM = tokenFCM;
	}

	public int getId() {
		
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Status getIsActive() {
		return isActive;
	}

	public void setIsActive(Status isActive) {
		this.isActive = isActive;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	
}
