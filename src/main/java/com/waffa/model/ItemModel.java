package com.waffa.model;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class ItemModel {

	
	private int itemId;
	private String itemDescriptionEn;
	private String itemDescriptionAr;
	private String itemTitleEn;
	private String itemTitleAr;
	private String price; 
	private String itemImageUrl;
	private int categoryId;
	
	
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemDescriptionEn() {
		return itemDescriptionEn;
	}
	public void setItemDescriptionEn(String itemDescriptionEn) {
		this.itemDescriptionEn = itemDescriptionEn;
	}
	public String getItemDescriptionAr() {
		return itemDescriptionAr;
	}
	public void setItemDescriptionAr(String itemDescriptionAr) {
		this.itemDescriptionAr = itemDescriptionAr;
	}
	public String getItemTitleEn() {
		return itemTitleEn;
	}
	public void setItemTitleEn(String itemTitleEn) {
		this.itemTitleEn = itemTitleEn;
	}
	public String getItemTitleAr() {
		return itemTitleAr;
	}
	public void setItemTitleAr(String itemTitleAr) {
		this.itemTitleAr = itemTitleAr;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getItemImageUrl() {
		return itemImageUrl;
	}
	public void setItemImageUrl(String itemImageUrl) {
		this.itemImageUrl = itemImageUrl;
	}
	
}
