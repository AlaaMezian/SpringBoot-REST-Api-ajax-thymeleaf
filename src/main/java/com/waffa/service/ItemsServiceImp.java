package com.waffa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waffa.entity.Items;
import com.waffa.exceptions.InternalServerErrorException;
import com.waffa.model.ItemModel;
import com.waffa.respository.ItemsRepository;

@Service
public class ItemsServiceImp implements ItemsService{

	@Autowired
	private ItemsRepository itemRepository;
	
	
	public List<ItemModel> getAllRelatedItems(int categoryId){
		List<Items> list = itemRepository.findAllByCategory(categoryId);
		List<ItemModel> mdlList = new ArrayList<ItemModel>();
		try {
			for(Items item :list) {
				ItemModel itemMdl=new ItemModel();
				itemMdl.setItemId(item.getItemId());
				itemMdl.setItemDescriptionAr(item.getItemDescriptionAr());
				itemMdl.setItemDescriptionEn(item.getItemDescriptionEn());
				itemMdl.setItemImageUrl(item.getItemImageUrl());
				itemMdl.setItemTitleAr(item.getItemTitleAr());
				itemMdl.setItemTitleEn(item.getItemTitleEn());
				itemMdl.setPrice(item.getPrice());
				mdlList.add(itemMdl);
			}
			
		}catch(Exception e)
		{
			throw new InternalServerErrorException("some went wron while fetching the items list");
		}
		return mdlList;
	}
}
