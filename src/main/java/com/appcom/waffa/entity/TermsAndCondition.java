package com.appcom.waffa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "termsandcondition")
public class TermsAndCondition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "terms_and_condition_Ar")
	private String termsAndConditionAr;
	
	@Column(name = "terms_and_condition_En")
	private String termsAndConditionEn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTermsAndConditionAr() {
		return termsAndConditionAr;
	}

	public void setTermsAndConditionAr(String termsAndConditionAr) {
		this.termsAndConditionAr = termsAndConditionAr;
	}

	public String getTermsAndConditionEn() {
		return termsAndConditionEn;
	}

	public void setTermsAndConditionEn(String termsAndConditionEn) {
		this.termsAndConditionEn = termsAndConditionEn;
	}


	
}
