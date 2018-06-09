package com.appcom.waffa.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcom.waffa.constant.Status;
import com.appcom.waffa.entity.Category;
import com.appcom.waffa.exceptions.InternalServerErrorException;
import com.appcom.waffa.exceptions.NotFoundException;
import com.appcom.waffa.model.CategoryModel;
import com.appcom.waffa.respository.CategoryRepository;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public List<CategoryModel> getCategories() {
		List<Category> categoriesList = categoryRepository.findAllByisActive(Status.Y);
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		try {
			for (Category category : categoriesList) {

				CategoryModel categoryModel = new CategoryModel();
				categoryModel.setCategoryid(category.getId());
				categoryModel.setCategoryNameAr(category.getCategoryNameAr());
				categoryModel.setCategoryNameEng(category.getCategoryNameEn());
				categoryModel.setCategoryDescriptionEn(category.getCategoryDescriptionEn());
				categoryModel.setCategoryDescriptionAr(category.getCategoryDescriptionAr());
				categoryModel.setImageUrl(category.getImageUrl());
				categories.add(categoryModel);

			}
		} catch (Exception e) {

			throw new InternalServerErrorException(
					"something went wrong while fetching the categories, more details: " + e.toString());
		}
		return categories;
	}


	@Override
	public void createCategory(CategoryModel catMdl) {

		try {

			Category category = new Category();
			category.setId(catMdl.getCategoryid());
			category.setCategoryNameAr(catMdl.getCategoryNameAr());
			category.setCategoryNameEn(catMdl.getCategoryNameEng());
			category.setImageUrl(catMdl.getImageUrl());
			category.setCategoryDescriptionAr(catMdl.getCategoryDescriptionAr());
			category.setCategoryDescriptionEn(catMdl.getCategoryDescriptionEn());
			category.setIsActive(Status.Y);
			categoryRepository.saveAndFlush(category);
		} catch (Exception e) {
			throw new InternalServerErrorException(
					"some thing went wrong when trying to save the object" + e.toString());
		}
	}

	@Override
	public String deleteCategory(CategoryModel catMdl) {
		try {
			Category cat = categoryRepository.findOneCategoryById(catMdl.getCategoryid());
			if (cat == null) {
				throw new NotFoundException("the category you are trying to delete does not exist");
			} else {
				cat.setIsActive(Status.N);
				categoryRepository.save(cat);
				return "Deleted";
			}
		} catch (Exception e) {
			throw new InternalServerErrorException("failed to delete item");
		}
	}

}
