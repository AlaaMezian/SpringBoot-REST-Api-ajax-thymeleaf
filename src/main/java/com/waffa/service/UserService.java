package com.waffa.service;

import com.waffa.entity.User;
import com.waffa.model.RegistrationModel;

public interface UserService {
	  
	 public User findUserByEmail(String email);
	 
	 public User findByUserName(String userName);
	 
	 public void saveUser(RegistrationModel registrationModel);
	 
	 
	}