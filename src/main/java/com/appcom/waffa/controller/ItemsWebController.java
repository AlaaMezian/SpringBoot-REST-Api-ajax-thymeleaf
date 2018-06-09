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
import com.appcom.waffa.model.ItemModel;
import com.appcom.waffa.service.CategoryService;
import com.appcom.waffa.service.ItemsService;

@Controller
@CrossOrigin(origins = "*")
public class ItemsWebController {

	@Autowired
	private ItemsService itemsService;

	@Autowired
	private CategoryService categoryService;

	// Linux: /home/{user}/Images/
	// System.getProperty("user.home") +/Images
	private static String UPLOAD_DIR = "/home/Images";

	@GetMapping(value = "/items")
	public ModelAndView getItems() {
		List<CategoryModel> categoryList = categoryService.getCategories();
		ModelAndView model = new ModelAndView();
		List<ItemModel> itemsList = itemsService.getItems();
		model.addObject("itemsList", itemsList);
		model.addObject("categoryList", categoryList);
		model.addObject("ItemModel", new ItemModel());
		model.setViewName("items");
		return model;
	}

	@GetMapping(value = "/item")
	@ResponseBody
	public List<ItemModel> getAllItemsModels() {
		return itemsService.getItems();
	}

	@RequestMapping(value = "/items", method = RequestMethod.POST)
	public ModelAndView createItems(HttpServletRequest request, @ModelAttribute("ItemModel") ItemModel itemModel) {
		String result = null;
		String appUrl = getAppUrl(request);
		String FullIMagePath = null;
		try {
			result = this.saveUploadedFiles(itemModel.getFiles());
			FullIMagePath = appUrl + result;

		} catch (IOException e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView();
		itemModel.setItemImageUrl(FullIMagePath);
		itemsService.createItem(itemModel);
		model.setViewName("items");
		return model;
	}

	@RequestMapping(value = "/items", method = RequestMethod.DELETE)
	public @ResponseBody String deleteItem(@RequestParam("ItemModel") int itemId, Model model) {
		ItemModel itemMdl = new ItemModel();
		itemMdl.setItemId(itemId);
		String result = "";
		result = itemsService.deleteItem(itemMdl);
		model.addAttribute("result", result);
		return result;

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
