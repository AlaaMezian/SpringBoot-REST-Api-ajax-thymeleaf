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

import com.waffa.model.LoginModel;
import com.waffa.security.AuthenticatedUser;
import com.waffa.security.UserDetailsServiceImpl;
import com.waffa.success.code.CommonSuccessCode;
import com.waffa.utils.CustomResponse;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private UserDetailsServiceImpl customUserDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> login(HttpServletRequest request,
			@Valid @RequestBody LoginModel loginModel) {
		
		AuthenticatedUser user =customUserDetailsService.loadUserByUsername(loginModel.getUsername());
        logger.info("custom api end point reached -------------------------------------------------");
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success,user),
				HttpStatus.OK);
	}
	
}
