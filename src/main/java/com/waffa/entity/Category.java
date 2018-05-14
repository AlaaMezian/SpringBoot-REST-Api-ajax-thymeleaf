package com.waffa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "category_name_ar")
	private String categoryNameAr;
	
	@Column(name = "category_name_en")
	private String categoryNameEn;
	
	@Column(name= "image_url")
	private String imageUrl;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "added_date")
	private Date addedDate;

	@OneToMany(mappedBy = "category")
	private List<Items> items;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	
	public String getCategoryNameAr() {
		return categoryNameAr;
	}

	public void setCategoryNameAr(String categoryNameAr) {
		this.categoryNameAr = categoryNameAr;
	}

	public String getCategoryNameEn() {
		return categoryNameEn;
	}

	public void setCategoryNameEn(String categoryNameEn) {
		this.categoryNameEn = categoryNameEn;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	private static final long serialVersionUID = 1L;

}
