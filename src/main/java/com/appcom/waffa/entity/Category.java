package com.appcom.waffa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "category_name_ar", nullable=false)
	private String categoryNameAr;
	
	@Column(name = "category_name_en", nullable=false)
	private String categoryNameEn;
	
	@Column(name= "image_url", nullable=false)
	private String imageUrl;


	@OneToMany
//	@JoinColumn(name = "category_id")
	private List<Items> items= new ArrayList<>();

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	private static final long serialVersionUID = 1L;

}
