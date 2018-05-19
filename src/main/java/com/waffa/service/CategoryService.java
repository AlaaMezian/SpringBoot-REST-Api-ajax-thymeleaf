package com.waffa.service;

import java.util.List;

import com.waffa.model.CategoryModel;

public interface CategoryService {

	public List<CategoryModel> getCategories();
	
	public void createCategory(CategoryModel catMdl);
	
	public String deleteCategory(CategoryModel catMdl);
}
