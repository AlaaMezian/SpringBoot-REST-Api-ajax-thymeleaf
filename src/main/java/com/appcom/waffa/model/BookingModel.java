package com.appcom.waffa.model;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.appcom.waffa.entity.Items;

@Validated
public class BookingModel {

	private int bookingId;
	private String bookingStartDate;
	private String bookingStartTime;
	private String bookingEndTime;
	private String userName;
	private List<Items> items;
	private String totalPrice;
    private int[] itemIds;


	public int[] getItemIds() {
		return itemIds;
	}

	public void setItemIds(int[] itemIds) {
		this.itemIds = itemIds;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingStartDate() {
		return bookingStartDate;
	}

	public void setBookingStartDate(String bookingStartDate) {
		this.bookingStartDate = bookingStartDate;
	}

	public String getBookingStartTime() {
		return bookingStartTime;
	}

	public void setBookingStartTime(String bookingStartTime) {
		this.bookingStartTime = bookingStartTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBookingEndTime() {
		return bookingEndTime;
	}

	public void setBookingEndTime(String bookingEndTime) {
		this.bookingEndTime = bookingEndTime;
	}

	

}
