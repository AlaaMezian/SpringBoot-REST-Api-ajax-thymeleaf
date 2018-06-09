package com.appcom.waffa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.appcom.waffa.constant.Status;

@Entity(name = "working_times")
public class WorkingTimes extends BaseEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "work_hour")
    private String times;
	
	@Column(name = "is_active")
	@Enumerated(EnumType.STRING)
	private Status isActive;
	

	public Status getIsActive() {
		return isActive;
	}

	public void setIsActive(Status isActive) {
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	
	
	
}
