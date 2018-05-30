package com.appcom.waffa.service;

import com.appcom.waffa.model.ChangePasswordModel;

public interface PasswordService {

	public void changePassword(int userId,ChangePasswordModel changeModel);
	public void forgetPassowrd(String email);
	
}
