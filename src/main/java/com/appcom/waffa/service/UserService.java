package com.appcom.waffa.service;

import com.appcom.waffa.entity.AdminUser;
import com.appcom.waffa.model.AdminUserModel;
import com.appcom.waffa.model.RegistrationModel;
import com.appcom.waffa.model.UserModel;

public interface UserService {
	  
	 public AdminUser findUserByEmail(String email);
	 
	 
	 public void saveUser(RegistrationModel registrationModel);
	 
	 public void editProfile(UserModel userModel, int id);
	 
	 public void signUpAdmin(AdminUserModel adminModel);
	 

	 
	 
	}