package com.waffa.model;

import org.springframework.validation.annotation.Validated;

@Validated
public class CategoryModel {
 
	private int id;
	private String categoryName;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
