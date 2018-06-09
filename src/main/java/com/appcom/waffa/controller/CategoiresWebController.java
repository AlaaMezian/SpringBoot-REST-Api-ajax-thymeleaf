package com.appcom.waffa.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.appcom.waffa.utils.ApplicationLogger;

@Controller
@CrossOrigin(origins = "*")
public class CategoiresWebController {

	@Autowired
	private CategoryService categoryService;


	public ApplicationLogger apploger;

	// Linux: /home/{user}/Images/
	// System.getProperty("user.home") + "Images"
	private static String UPLOAD_DIR = "/home/Images";


	// @Value("${leftNav.categories}")
	// private String categories;

	@GetMapping(value = "/categories")
	public ModelAndView getCategoriesList() {
		ModelAndView model = new ModelAndView("/Partials/categoryPartial");
		List<CategoryModel> categoryList = categoryService.getCategories();
		CategoryModel categoryModel = new CategoryModel();
		model.setViewName("categories");
		model.addObject("categoryList", categoryList);
		model.addObject("categoryModel", categoryModel);
		return model;
	}

	/// RedirectAttributes redirectAttributes, BindingResult bindingResult
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public ModelAndView createCategory(HttpServletRequest request,
			@ModelAttribute("categoryModel") CategoryModel catMdl) {
		String result = null;
		String appUrl = getAppUrl(request);
		String FullIMagePath = null;
		try {
			result = this.saveUploadedFiles(catMdl.getFiles());
			FullIMagePath = appUrl + result;

		} catch (IOException e) {
			e.printStackTrace();
		}

		ModelAndView model = new ModelAndView();
		catMdl.setImageUrl(FullIMagePath);
		categoryService.createCategory(catMdl);
		// List<CategoryModel> catList = categoryService.getCategories();
		// model.addObject("categoryList", catList);
		model.setViewName("categories");
		return model;
	}

	@RequestMapping(value = "/deleteCategories", method = RequestMethod.GET)
	public @ResponseBody String deleteCat(@RequestParam("categoryId") int catId, Model model) {
		CategoryModel catMdl = new CategoryModel();
		catMdl.setCategoryid(catId);
		String result = "";
		result = categoryService.deleteCategory(catMdl);
		model.addAttribute("result", result);
		return result;

	}
	

	@GetMapping(value = "/category")
	@ResponseBody
	public List<CategoryModel> getAllCategories() {
		return categoryService.getCategories();
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	// Save Files
	private String saveUploadedFiles(MultipartFile[] files) throws IOException {

		// Make sure directory exists!
		File uploadDir = new File(UPLOAD_DIR);
		String fileName = null;
		uploadDir.mkdirs();

		StringBuilder sb = new StringBuilder();
		String uploadFilePath = null;
		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue;
			}
			uploadFilePath = UPLOAD_DIR + "/" + file.getOriginalFilename();
			fileName = file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			Path path = Paths.get(uploadFilePath);
			Files.write(path, bytes);

			sb.append(uploadFilePath);
			// .append("<br/>"); for mutiple images

		}
		// return sb.toString();

		return uploadFilePath;
	}
}
