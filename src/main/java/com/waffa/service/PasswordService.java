package com.waffa.service;

import com.waffa.model.ChangePasswordModel;

public interface PasswordService {

	public void changePassword(int userId,ChangePasswordModel changeModel);
	public void forgetPassowrd(String email);
	
}
