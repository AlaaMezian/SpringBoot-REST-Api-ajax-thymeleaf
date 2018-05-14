package com.waffa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter	
public class Items implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private int itemId;
	
	@Column(name = "item_title_en")
	private String itemTitleEn;
	
	@Column(name = "item_title_ar")
	private String itemTitleAr;
	
	@Column(name = "item_description_ar")
	private String itemDescriptionAr;
	
	@Column(name = "item_description_en")
	private String itemDescriptionEn;
	
	@Column(name = "item_image_url")
	private String itemImageUrl;
	
	@Column(name = "price")
	private String price;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name= "added_date")
	private Date addedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private Category category;
	
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemTitleEn() {
		return itemTitleEn;
	}

	public void setItemTitleEn(String itemTitleEn) {
		this.itemTitleEn = itemTitleEn;
	}

	public String getItemTitleAr() {
		return itemTitleAr;
	}

	public void setItemTitleAr(String itemTitleAr) {
		this.itemTitleAr = itemTitleAr;
	}

	public String getItemDescriptionAr() {
		return itemDescriptionAr;
	}

	public void setItemDescriptionAr(String itemDescriptionAr) {
		this.itemDescriptionAr = itemDescriptionAr;
	}

	public String getItemDescriptionEn() {
		return itemDescriptionEn;
	}

	public void setItemDescriptionEn(String itemDescriptionEn) {
		this.itemDescriptionEn = itemDescriptionEn;
	}

	public String getItemImageUrl() {
		return itemImageUrl;
	}

	public void setItemImageUrl(String itemImageUrl) {
		this.itemImageUrl = itemImageUrl;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

}
