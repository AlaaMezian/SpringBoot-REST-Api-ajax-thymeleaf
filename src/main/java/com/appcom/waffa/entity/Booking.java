package com.appcom.waffa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.appcom.waffa.constant.Status;

@Entity
@Table(name = "booking")
public class Booking extends BaseEntity implements Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "booking_start_date",nullable=false)
	private Date startDate;
	
	@Column(name = "booking_start_time")
	private String StartTime;
	
	@Column(name = "booking_end_time")
	private String EndTime;
	
	@ManyToMany(cascade = CascadeType.ALL)
	 private List<Items> item = new ArrayList<>();	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "is_active")
	@Enumerated(EnumType.STRING)
	private Status isActive;

	@Column(name = "is_pending")
	@Enumerated(EnumType.STRING)
	private Status isPending;

	@Column(name = "is_cancled")
	@Enumerated(EnumType.STRING)
	private Status isCancled;
	
	@Column(name = "is_done")
	@Enumerated(EnumType.STRING)
	private Status isDone;
	
	private static final long serialVersionUID = 1L;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public List<Items> getItem() {
		return item;
	}

	public void setItem(List<Items> item) {
		this.item = item;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getIsActive() {
		return isActive;
	}

	public void setIsActive(Status isActive) {
		this.isActive = isActive;
	}

	public Status getIsCancled() {
		return isCancled;
	}

	public void setIsCancled(Status isCancled) {
		this.isCancled = isCancled;
	}

	public Status getIsDone() {
		return isDone;
	}

	public void setIsDone(Status isDone) {
		this.isDone = isDone;
	}
	
	public Status getIsPending() {
		return isPending;
	}

	public void setIsPending(Status isPending) {
		this.isPending = isPending;
	}

	
	
}
