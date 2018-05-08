package com.waffa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waffa.entity.Category;
import com.waffa.exception.code.CategoriesExceptionCode;
import com.waffa.exceptions.CategoriesException;
import com.waffa.model.CategoryModel;
import com.waffa.respository.CategoryRepository;

@Service("categoryService")	
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryModel> getCategories()
	{
		List<Category> categoriesList = categoryRepository.findAll();
		List<CategoryModel> categories= new ArrayList<CategoryModel	>();
		int id = categoriesList.size();
		try {
			for (Category category : categoriesList) {

				CategoryModel categoryModel = new CategoryModel();
				categoryModel.setId(id--);
				categoryModel.setCategoryName(category.getCategoryName());
				categories.add(categoryModel);

			}
		} catch (Exception e) {

			throw new CategoriesException(CategoriesExceptionCode.ERROR_RETRIVING_LIST, e.toString());
		}
		return categories;
	}

	
	
}
