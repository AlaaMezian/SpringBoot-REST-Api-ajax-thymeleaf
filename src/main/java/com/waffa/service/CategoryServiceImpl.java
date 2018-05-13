package com.waffa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waffa.entity.Category;
import com.waffa.exceptions.InternalServerErrorException;
import com.waffa.model.CategoryModel;
import com.waffa.respository.CategoryRepository;

@Service("categoryService")	
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryModel> getCategories()
	{
		List<Category> categoriesList = categoryRepository.findAll();
		List<CategoryModel> categories= new ArrayList<CategoryModel>();
		try {
			for (Category category : categoriesList) {

				CategoryModel categoryModel = new CategoryModel();
				categoryModel.setId(category.getCategoryId());
				categoryModel.setCategoryNameAr(category.getCategoryNameAr());
				categoryModel.setCategoryNameEng(category.getCategoryNameEn());
				categoryModel.setImageUrl(category.getImageUrl());
				categories.add(categoryModel);

			}
		} catch (Exception e) {

			throw new InternalServerErrorException("something went wrong while fetching the categories, more details: "+ e.toString());
		}
		return categories;
	}

	
	
}
