package com.appcom.waffa.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcom.waffa.constant.Status;
import com.appcom.waffa.entity.Booking;
import com.appcom.waffa.entity.Items;
import com.appcom.waffa.entity.User;
import com.appcom.waffa.exceptions.BadRequestException;
import com.appcom.waffa.exceptions.InternalServerErrorException;
import com.appcom.waffa.model.BookingModel;
import com.appcom.waffa.respository.BookingRepository;
import com.appcom.waffa.respository.ItemsRepository;
import com.appcom.waffa.respository.UserRepository;
import com.appcom.waffa.utils.AppConstants;
import com.appcom.waffa.utils.DateUtil;;

@Service("bookingService")
public class BookingServiceImp implements BookingService {

	private final static Logger logger = LoggerFactory.getLogger(BookingServiceImp.class);

	@Autowired
	public BookingRepository bookingRepository;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public ItemsRepository itemsRepository;

	@Override
	public List<BookingModel> getAllActiveBookingPerUser(int userId) {
		try {
			User user = userRepository.findById(userId);
			List<Booking> bookingList = bookingRepository.findAllBookingByUserAndIsActive(user, Status.Y);
			List<BookingModel> bookingModelList = new ArrayList<BookingModel>();
			int totalPrice = 0;
			for (Booking book : bookingList) {
				BookingModel bookingMdl = new BookingModel();
				bookingMdl.setBookingId(book.getId());
				bookingMdl.setUserName(user.getUsername());
				bookingMdl.setBookingStartTime(book.getStartTime());
				bookingMdl.setBookingEndTime(book.getEndTime());
				List<Items> associatedItems = book.getItem();
				bookingMdl.setItems(associatedItems);
				for (Items item : associatedItems) {
					totalPrice += Integer.parseInt(item.getPrice());
				}
				bookingMdl.setTotalPrice(Integer.toString(totalPrice));
				totalPrice = 0;
				bookingMdl.setBookingStartDate(
						DateUtil.convertDatetoString(book.getStartDate(), AppConstants.DATE_FORMAT_DDMMYYYY));
				bookingModelList.add(bookingMdl);
			}

			return bookingModelList;
		} catch (Exception e) {
			throw new InternalServerErrorException("error while fetching Data from data base ");
		}
	}

	@Override
	public boolean cancleBooking(BookingModel bookingMdl) {
		try {
			Booking book = bookingRepository.findOneById(bookingMdl.getBookingId());
			book.setIsCancled(Status.Y);
			book.setIsActive(Status.N);
			book.setIsDone(Status.N);
			book.setIsPending(Status.N);
			bookingRepository.save(book);
			return true;
		} catch (Exception e) {
			throw new InternalServerErrorException("some thing went wrong while trying to update entity");
		}
	}

	@Override
	public List<BookingModel> getAllDoneBooking(int userId) {

		try {
			User user = userRepository.findById(userId);
			List<Booking> bookingList = bookingRepository.findAllBookingByUserAndIsDone(user, Status.Y);
			List<BookingModel> bookingModelList = new ArrayList<BookingModel>();
			int totalPrice = 0;

			for (Booking book : bookingList) {
				BookingModel bookingMdl = new BookingModel();
				bookingMdl.setBookingId(book.getId());
				bookingMdl.setUserName(user.getUsername());
				List<Items> associatedItems = book.getItem();
				bookingMdl.setItems(associatedItems);
				for (Items item : associatedItems) {
					totalPrice += Integer.parseInt(item.getPrice());
				}
				bookingMdl.setTotalPrice(Integer.toString(totalPrice));
				totalPrice = 0;
				bookingMdl.setBookingStartTime(book.getStartTime());
				bookingMdl.setBookingEndTime(book.getEndTime());
				bookingMdl.setBookingStartDate(
						DateUtil.convertDatetoString(book.getStartDate(), AppConstants.DATE_FORMAT_DDMMYYYY));
				bookingModelList.add(bookingMdl);
			}

			return bookingModelList;
		} catch (Exception e) {
			throw new InternalServerErrorException("error while fetching Data from data base ");
		}
	}

	@Override
	public int numberOfCancledBookingPerUser(int userId) {
		User user = userRepository.findById(userId);
		List<Booking> cancledBooking = bookingRepository.findAllBookingByUserAndIsCancled(user, Status.Y);
		int numberOfCancledBooking = cancledBooking.size();

		return numberOfCancledBooking;
	}

	@Override
	public Booking BookNewAppoitment(BookingModel bookingMdl, int userId) {
		User user = userRepository.findById(userId);
		List<Items> items = itemsRepository.findAllItemsByIds(bookingMdl.getItemIds());
		Booking Appointment = new Booking();
		Appointment.setIsActive(Status.N);
		Appointment.setIsCancled(Status.N);
		Appointment.setIsDone(Status.N);
		Appointment.setIsPending(Status.Y);
		Appointment.setStartDate(DateUtil.convertStringToDate(bookingMdl.getBookingStartDate()));
		String startTime = bookingMdl.getBookingStartTime();
		Appointment.setStartTime(startTime);
		String endTime = bookingMdl.getBookingEndTime();
		Appointment.setEndTime(endTime);

		SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); // if 24 hour format
		try {
			java.util.Date d1 = (java.util.Date) format.parse(startTime);
			java.util.Date d2 = (java.util.Date) format.parse(endTime);
			java.sql.Time firstTime = new java.sql.Time(d1.getTime());
			java.sql.Time seconedTime = new java.sql.Time(d2.getTime());
			if (seconedTime.before(firstTime)) {
				throw new BadRequestException("Bad Request Error");

			}
		} catch (Exception e) {
			throw new BadRequestException("the time of the booking is not valid ,please check and try again later");
		}

		Appointment.setUser(user);

		Appointment.setItem(items);
		bookingRepository.saveAndFlush(Appointment);
		return Appointment;

	}

	@Override
	public List<BookingModel> getAllPendingAppointment() {
		try {
			List<Booking> bookingList = bookingRepository.findAllByIsPending(Status.Y);

			List<BookingModel> bookingModelList = new ArrayList<BookingModel>();
			int totalPrice = 0;
			for (Booking book : bookingList) {
				BookingModel bookingMdl = new BookingModel();
				User user = book.getUser();
				bookingMdl.setUserName(user.getUsername());
				bookingMdl.setBookingId(book.getId());
				bookingMdl.setBookingStartTime(book.getStartTime());
				bookingMdl.setBookingEndTime(book.getEndTime());
				List<Items> associatedItems = book.getItem();
				bookingMdl.setItems(associatedItems);
				for (Items item : associatedItems) {
					totalPrice += Integer.parseInt(item.getPrice());
					logger.info("watching how much the total price is " + totalPrice);
				}
				bookingMdl.setTotalPrice(Integer.toString(totalPrice));
				totalPrice = 0;

				bookingMdl.setBookingStartDate(
						DateUtil.convertDatetoString(book.getStartDate(), AppConstants.DATE_FORMAT_DDMMYYYY));

				bookingModelList.add(bookingMdl);
			}

			return bookingModelList;
		} catch (Exception e) {
			throw new InternalServerErrorException("error while fetching Data from data base ");
		}
	}

	@Override
	public List<String> getAllAvailableTimes(String date) {
		Date dateToFiltter = DateUtil.convertStringToDate(date);
		List<Booking> bookingList = bookingRepository.findAllBookingByStartDate(dateToFiltter);
		ArrayList<String> availableTime = new ArrayList<String>() {
			{
				add("10:00 am");
				add("11:00 am");
				add("12:00 pm");
				add("01:00 pm");
				add("02:00pm");
				add("03:00 pm");
				add("04:00 pm");
				add("05:00 pm");
				add("06:00 pm");
				add("07:00 pm");
				add("08:00 pm");
				add("09:00 pm");
				add("10:00 pm");
			}
		};

		List<String> unAvailableTimeList = new ArrayList<>();
		String bookingStartTime;
		String bookingEndTime;
		for (Booking book : bookingList) {
			bookingStartTime = book.getStartTime();
			bookingEndTime = book.getEndTime();
			unAvailableTimeList.add(bookingStartTime);
			unAvailableTimeList.add(bookingEndTime);
		}

		availableTime.removeAll(unAvailableTimeList);
		return availableTime;
	}

}
