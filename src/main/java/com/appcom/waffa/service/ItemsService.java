package com.appcom.waffa.service;

import java.util.List;

import com.appcom.waffa.model.ItemModel;

public interface ItemsService {

	public List<ItemModel> getAllRelatedItems(int categoryId);
	
	public void createItem(ItemModel itmMdl);
	
	public String deleteItem(ItemModel itmMdl);
	
	public List<ItemModel> getItems();
}
