package com.appcom.waffa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.appcom.waffa.constant.Status;



@Entity
@Table(name = "admin_user")
public class AdminUser implements Serializable	{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "admin_user_id")
	private int id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name="enc_password")
	private String password;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "is_active")
	@Enumerated(EnumType.STRING)
	private Status isActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	
}
