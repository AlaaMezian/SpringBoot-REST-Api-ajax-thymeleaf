package com.appcom.waffa.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appcom.waffa.model.ChangePasswordModel;
import com.appcom.waffa.service.PasswordService;
import com.appcom.waffa.success.code.CommonSuccessCode;
import com.appcom.waffa.utils.CustomResponse;

@RestController
@RequestMapping("/api/v1")
public class PasswordController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PasswordService passwordService;
	
	@RequestMapping(value = "/user/{id}/changepassword", method = RequestMethod.PUT)
	public  ResponseEntity<CustomResponse> changePass(@PathVariable("id") int userId,
			@Valid @RequestBody ChangePasswordModel changeModel) 
	{
		passwordService.changePassword(userId, changeModel);
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success),
				HttpStatus.OK);
	}
	
	 
	@RequestMapping(value="/forgetpassword",method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> forgetPass(@RequestParam String email)
	{
		passwordService.forgetPassowrd(email);
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success),
				HttpStatus.OK);
	}
}
