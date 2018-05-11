package com.waffa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.waffa.constant.Status;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "admin_user")
@Setter
@Getter
public class AdminUser implements Serializable	{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "admin_user_id")
	private int adminUserId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name="enc_password")
	private String password;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "is_active")
	@Enumerated(EnumType.STRING)
	private Status isActive;
	
}
