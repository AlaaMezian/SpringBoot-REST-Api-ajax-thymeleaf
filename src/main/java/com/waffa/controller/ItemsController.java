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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.waffa.model.CategoryModel;
import com.waffa.model.ItemModel;
import com.waffa.service.ItemsService;
import com.waffa.success.code.CommonSuccessCode;
import com.waffa.utils.CustomResponse;

@RestController
@RequestMapping("/api/v1")
public class ItemsController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ItemsService itemsService;
	
	@RequestMapping(value = "/category/{id}/items", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> getItems(@PathVariable("id") int categoryId){
		List<ItemModel> itemsList = itemsService.getAllRelatedItems(categoryId);
		logger.info("reached **********************");
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success, itemsList),
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/item", method = RequestMethod.POST)
	public ModelAndView createCategory(@ModelAttribute("items") ItemModel itemModel,
			BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		itemsService.createItem(itemModel);
		List<ItemModel> itemList = itemsService.getItems();
		model.addObject("itemsList", itemList);
		return model;
	}
	
	@RequestMapping(value="/item", method = RequestMethod.DELETE)
	public @ResponseBody String deleteItem(@RequestParam("itemId") int itemId,Model model)
	{
		ItemModel itemMdl = new ItemModel();
		itemMdl.setItemId(itemId);
		String result= "";
		result = itemsService.deleteItem(itemMdl);
		model.addAttribute("result", result);
		return result;

	}
	
	@RequestMapping(value = "/items")
	@ResponseBody
	public List<ItemModel> getAllItems()
	{
		return itemsService.getItems();
	}

}
