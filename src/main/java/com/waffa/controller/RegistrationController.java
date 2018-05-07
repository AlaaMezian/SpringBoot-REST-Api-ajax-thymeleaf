package com.waffa.controller;

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

import com.waffa.model.RegistrationModel;
import com.waffa.service.UserService;
import com.waffa.success.code.CommonSuccessCode;
import com.waffa.utils.CustomResponse;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> signup(HttpServletRequest request,
			@Valid @RequestBody RegistrationModel registrationModel) {
        userService.saveUser(registrationModel);
        logger.info("everything went Fine until this line");
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success),
				HttpStatus.OK);
	}
}
