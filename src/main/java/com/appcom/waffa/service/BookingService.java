package com.appcom.waffa.service;

import java.util.List;

import com.appcom.waffa.entity.Booking;
import com.appcom.waffa.model.BookingModel;

public interface BookingService {

	public List<BookingModel> getAllActiveBookingPerUser (int userId);
	public boolean cancleBooking(BookingModel bookingMdl); 
	public List<BookingModel> getAllDoneBooking(int userId);
	
	public int numberOfCancledBookingPerUser(int userId);
	
	public Booking BookNewAppoitment(BookingModel bookingMdl, int userId);
	
	//FOR ADMIN
	public List<BookingModel> getAllPendingAppointment();
	
	public List<String> getAllAvailableTimes(String date);
}
