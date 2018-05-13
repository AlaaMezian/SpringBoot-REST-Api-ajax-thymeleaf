package com.waffa.service;

import java.util.List;

import com.waffa.model.ItemModel;

public interface ItemsService {

	public List<ItemModel> getAllRelatedItems(int categoryId);
}
