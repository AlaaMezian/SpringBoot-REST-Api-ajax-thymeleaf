package com.waffa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success, itemsList),
				HttpStatus.OK);
	}

}
