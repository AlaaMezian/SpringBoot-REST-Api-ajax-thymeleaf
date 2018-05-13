package com.waffa.service;

import com.waffa.model.ChangePasswordModel;
import com.waffa.model.ForgetPasswordModel;

public interface PasswordService {

	public void changePassword(int userId,ChangePasswordModel changeModel);
	public void forgetPassowrd(String email,ForgetPasswordModel forgetPssMdl);
	
}
