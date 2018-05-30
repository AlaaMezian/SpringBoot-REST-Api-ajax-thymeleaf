package com.appcom.waffa.service;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.appcom.waffa.entity.Category;
import com.appcom.waffa.exceptions.BadRequestException;
import com.appcom.waffa.exceptions.InternalServerErrorException;
import com.appcom.waffa.exceptions.NotFoundException;
import com.appcom.waffa.model.CategoryModel;
import com.appcom.waffa.respository.CategoryRepository;
import com.appcom.waffa.utils.CoreValidations;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public List<CategoryModel> getCategories() {
		List<Category> categoriesList = categoryRepository.findAll();
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		try {
			for (Category category : categoriesList) {

				CategoryModel categoryModel = new CategoryModel();
				categoryModel.setId(category.getId());
				categoryModel.setCategoryNameAr(category.getCategoryNameAr());
				categoryModel.setCategoryNameEng(category.getCategoryNameEn());
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

			if (!CoreValidations.validArabic(catMdl.getCategoryNameAr())) {
				throw new BadRequestException("please enter valid arabic letters");
			}

			Category category = new Category();
			category.setCategoryNameAr(catMdl.getCategoryNameAr());
			category.setCategoryNameEn(catMdl.getCategoryNameEng());
			category.setImageUrl(catMdl.getImageUrl());
			category.setId(catMdl.getId());
			categoryRepository.saveAndFlush(category);
		} catch (Exception e) {
			throw new InternalServerErrorException(
					"some thing went wrong when trying to save the object" + e.toString());
		}
	}

	@Override
	public String deleteCategory(CategoryModel catMdl) {
		try {
			Category cat = categoryRepository.findOneCategoryById(catMdl.getId());
			if (cat == null) {
				throw new NotFoundException("the category you are trying to delete does not exist");
			} else {
				categoryRepository.delete(cat);
				return "Deleted";
			}
		} catch (Exception e) {
			throw new InternalServerErrorException("failed to delete item");
		}
	}

}
