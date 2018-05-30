package com.appcom.waffa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.appcom.waffa.model.CategoryModel;
import com.appcom.waffa.service.CategoryService;

@Controller
public class CategoiresWebController {

	@Autowired
	private CategoryService categoryService;

//	@Value("${leftNav.categories}")
//	private String categories;

	@GetMapping(value = "/categories")
	public ModelAndView getMerchantKycDocument() {
		ModelAndView model = new ModelAndView();
		model.setViewName("categories");
		model.addObject("categoryModel",new CategoryModel());
//		model.addObject("documentNameList", merchantDocsService.getDocNameList(merchantAcctId));
		return model;
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public ModelAndView createCategory(@ModelAttribute("category") CategoryModel catMdl,
			@RequestParam("file") MultipartFile file, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		categoryService.createCategory(catMdl);
		List<CategoryModel> catList = categoryService.getCategories();

		model.addObject("categoryList", catList);
		return model;
	}

	@RequestMapping(value = "/category", method = RequestMethod.DELETE)
	public @ResponseBody String deleteCat(@RequestParam("categoryId") int catId, Model model) {
		CategoryModel catMdl = new CategoryModel();
		catMdl.setId(catId);
		String result = "";
		result = categoryService.deleteCategory(catMdl);
		model.addAttribute("result", result);
		return result;

	}

	@RequestMapping(value = "/category")
	@ResponseBody
	public List<CategoryModel> getAllCategories() {
		return categoryService.getCategories();
	}

}
