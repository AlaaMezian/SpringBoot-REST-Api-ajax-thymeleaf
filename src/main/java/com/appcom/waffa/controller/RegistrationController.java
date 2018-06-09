package com.appcom.waffa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appcom.waffa.model.AdminUserModel;
import com.appcom.waffa.model.RegistrationModel;
import com.appcom.waffa.service.UserService;
import com.appcom.waffa.success.code.CommonSuccessCode;
import com.appcom.waffa.utils.CustomResponse;

@RestController
public class RegistrationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/api/v1/signup", method = RequestMethod.POST,produces = "application/json")
	public ResponseEntity<CustomResponse> signup(HttpServletRequest request,
			@Valid @RequestBody RegistrationModel registrationModel) {
		userService.saveUser(registrationModel);
		logger.info("everything went Fine until this line");
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success), HttpStatus.OK);
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST,produces = "application/json")
	public ResponseEntity<CustomResponse> createAdminUser(@Valid @RequestBody AdminUserModel user) {
		userService.signUpAdmin(user);
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success), HttpStatus.OK);

	}
}
