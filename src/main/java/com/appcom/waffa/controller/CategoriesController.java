package com.appcom.waffa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.appcom.waffa.model.CategoryModel;
import com.appcom.waffa.service.CategoryService;
import com.appcom.waffa.success.code.CommonSuccessCode;
import com.appcom.waffa.utils.CustomResponse;

@RestController
@RequestMapping("/api/v1")
public class CategoriesController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> getCategories() {
		List<CategoryModel> categoriesList = categoryService.getCategories();
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success, categoriesList),
				HttpStatus.OK);
	}

	
}
