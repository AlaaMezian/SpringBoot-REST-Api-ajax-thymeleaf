package com.waffa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Column(name = "item_title")
	private String itemTitle;
	
	@Column(name = "item_description")
	private String itemDescription;
	
	@Column(name = "price")
	private String price;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name= "added_date")
	private Date addedDate;
	
	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name="category_id")
	private Category categoryId;
	

}
