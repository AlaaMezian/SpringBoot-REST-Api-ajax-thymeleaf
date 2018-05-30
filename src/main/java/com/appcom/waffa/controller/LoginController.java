package com.appcom.waffa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hamcrest.text.IsEmptyString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appcom.waffa.entity.AdminUser;
import com.appcom.waffa.model.AdminUserModel;
import com.appcom.waffa.model.LoginModel;
import com.appcom.waffa.security.AuthenticatedUser;
import com.appcom.waffa.security.UserDetailsServiceImpl;
import com.appcom.waffa.service.UserService;
import com.appcom.waffa.success.code.CommonSuccessCode;
import com.appcom.waffa.utils.CustomResponse;

@RestController
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsServiceImpl customUserDetailsService;

	@Autowired
	@Lazy
	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping(value = "/api/v1/login", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> login(HttpServletRequest request, @Valid @RequestBody LoginModel loginModel) {
		logger.info("custom api end point reached -------------------------------------------------");
		AuthenticatedUser user = customUserDetailsService.loadUserByUsername(loginModel.getUsername());
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success, user), HttpStatus.OK);
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();

		model.addObject("adminUserModel", new AdminUserModel());
		model.setViewName("login");
		return model;
	}
    
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginAdmin(@ModelAttribute("adminUserModel") AdminUserModel adminUserModel,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("login");
		AdminUser admin = userService.findUserByEmail(adminUserModel.getUserEmail());
         
		if ( admin == null) {
		   admin = new AdminUser();
			bindingResult.rejectValue("userEmail", "error.adminUserModel", "This email is not valid !");
		}

		if (!passwordEncoder.matches(adminUserModel.getPass(), admin.getPassword())) {
			bindingResult.rejectValue("pass", "error.adminUserModel", "worng password please try again!");
		}
        
		if (bindingResult.hasErrors()) {
        return modelAndView;
		}else {
			ModelAndView modelAndViewAdmin =  new ModelAndView("redirect:/adminHome");
			return modelAndViewAdmin;
		}
	}
	

	@RequestMapping(value = { "/access_denied" }, method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("errors/access_denied");
		return model;
	}

}
