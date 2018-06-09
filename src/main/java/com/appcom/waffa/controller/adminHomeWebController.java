package com.appcom.waffa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class adminHomeWebController {

	@GetMapping(value = "/adminHome")
	public ModelAndView adminHome() {
		ModelAndView model = new ModelAndView("adminHome");
		return model;
	}

	
}
