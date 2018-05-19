package com.waffa.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.waffa.model.CategoryModel;
import com.waffa.service.CategoryService;
import com.waffa.success.code.CommonSuccessCode;
import com.waffa.utils.CustomResponse;

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

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public ModelAndView createCategory(@ModelAttribute("category") CategoryModel  catMdl,
			BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		categoryService.createCategory(catMdl);
		List<CategoryModel> catList = categoryService.getCategories();
		
		model.addObject("categoryList", catList);
		return model;
	}
	
	@RequestMapping(value="/category", method = RequestMethod.DELETE)
	public @ResponseBody String deleteCat(@RequestParam("categoryId") int catId,Model model)
	{
		CategoryModel catMdl = new CategoryModel();
		catMdl.setId(catId);
		String result= "";
		result = categoryService.deleteCategory(catMdl);
		model.addAttribute("result", result);
		return result;

	}
	
	@RequestMapping(value = "/category")
	@ResponseBody
	public List<CategoryModel> getAllCategories()
	{
		return categoryService.getCategories();
	}
}
