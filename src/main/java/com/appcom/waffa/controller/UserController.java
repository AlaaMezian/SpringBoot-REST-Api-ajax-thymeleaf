package com.appcom.waffa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appcom.waffa.model.UserModel;
import com.appcom.waffa.service.UserService;
import com.appcom.waffa.success.code.CommonSuccessCode;
import com.appcom.waffa.utils.CustomResponse;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "user/{id}/profile", method = RequestMethod.PUT,produces = "application/json")
	public ResponseEntity<CustomResponse> editProfile(@Valid @RequestBody UserModel userModel ,@PathVariable("id") int id){
		userService.editProfile(userModel,id);
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success ),
				HttpStatus.OK);	
	}
}
