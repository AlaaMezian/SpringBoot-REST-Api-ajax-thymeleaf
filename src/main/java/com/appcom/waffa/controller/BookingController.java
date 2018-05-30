package com.appcom.waffa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appcom.waffa.entity.Booking;
import com.appcom.waffa.model.BookingModel;
import com.appcom.waffa.service.BookingService;
import com.appcom.waffa.success.code.CommonSuccessCode;
import com.appcom.waffa.utils.CustomResponse;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@RequestMapping(value = "/user/{id}/appointment/active", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> getAllActiveAppointment(@PathVariable("id") int userId) {
		List<BookingModel> appointmentList = bookingService.getAllActiveBookingPerUser(userId);
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success, appointmentList),
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}/NumberOfCancledBooking", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> getAllCancledAppointmentCount(@PathVariable("id") int userId) {
		int appointmentList = bookingService.numberOfCancledBookingPerUser(userId);
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success, appointmentList),
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}/appointment/done", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> getAllDoneAppointment(@PathVariable("id") int userId) {
		List<BookingModel> appointmentList = bookingService.getAllDoneBooking(userId);
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success, appointmentList),
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "user/{id}/appointment/cancel", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> cancleBooking(@Valid @RequestBody BookingModel bookingMdl){
		bookingService.cancleBooking(bookingMdl);
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success , "message : " + "appointment cancled Successfully"),
				HttpStatus.OK);	
	}
	
	@RequestMapping(value = "user/{id}/appointment/book", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> bookAppointment(@Valid @RequestBody BookingModel bookingMdl ,@PathVariable("id") int userId){
		Booking newAppointment = bookingService.BookNewAppoitment(bookingMdl,userId);
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Created ,newAppointment ),
				HttpStatus.OK);	
	}
	//for Admin and might convert it to return list only 
	@RequestMapping(value = "booking/pending" , method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> getAllPendingBooking()
	{
		List<BookingModel> pendingAppointments = bookingService.getAllPendingAppointment();
		return new ResponseEntity<CustomResponse> (new CustomResponse(CommonSuccessCode.Success ,pendingAppointments), HttpStatus.OK);
	}
	
	@RequestMapping(value = "appointment/availableTimes",method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> getAvailableTimes(@RequestParam("date") String date )
	{
		List<String> availableTimes = bookingService.getAllAvailableTimes(date);
		return new ResponseEntity<CustomResponse> ( new CustomResponse(CommonSuccessCode.Success , availableTimes) ,HttpStatus.OK);
	}
	
	

}
