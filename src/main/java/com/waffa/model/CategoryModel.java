package com.waffa.model;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class CategoryModel {
 
	private int id;
	@NotNull
	private String categoryNameAr;
	@NotNull
	private String categoryNameEng;
	@NotNull
	private String imageUrl;
	
	
	public String getCategoryNameAr() {
		return categoryNameAr;
	}
	public void setCategoryNameAr(String categoryNameAr) {
		this.categoryNameAr = categoryNameAr;
	}
	public String getCategoryNameEng() {
		return categoryNameEng;
	}
	public void setCategoryNameEng(String categoryNameEng) {
		this.categoryNameEng = categoryNameEng;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	
}
