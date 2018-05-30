package com.appcom.waffa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.appcom.waffa.model.CategoryModel;


@Controller
public class adminHomeWeController {

	@GetMapping(value = "/adminHome")
	public ModelAndView adminHome() {
		ModelAndView model = new ModelAndView("adminHome");
		return model;
	}

	
}
