package com.appcom.waffa.model;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Validated
public class CategoryModel {
 
	
	private int categoryid;
	
	private String categoryNameEng;
	
	private String categoryNameAr;
	
	private String categoryDescriptionEn;
	
	private String categoryDescriptionAr ;
	
	private String imageUrl;
	
    private MultipartFile[] files;
	
	
	public MultipartFile[] getFiles() {
		return files;
	}

	public String getCategoryDescriptionAr() {
		return categoryDescriptionAr;
	}

	public void setCategoryDescriptionAr(String categoryDescriptionAr) {
		this.categoryDescriptionAr = categoryDescriptionAr;
	}

	public String getCategoryDescriptionEn() {
		return categoryDescriptionEn;
	}
	public void setCategoryDescriptionEn(String categoryDescriptionEn) {
		this.categoryDescriptionEn = categoryDescriptionEn;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
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

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}


	
	
}
