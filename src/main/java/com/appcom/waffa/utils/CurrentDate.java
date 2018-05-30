package com.appcom.waffa.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CurrentDate {

	// method return current date
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * Get a diff between two dates
	 * 
	 * @param date1
	 *            the oldest date
	 * @param date2
	 *            the newest date
	 * @param timeUnit
	 *            the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(Date oldDate, Date newDate, TimeUnit timeUnit) {
		long diffInMillies = newDate.getTime() - oldDate.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	final static String DATE_FORMAT = "dd-MM-yyyy";

	public static boolean isDateValid(String fromDate, String toDate) {
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(fromDate);
			df.parse(toDate);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static String formatDate(Date date) {
		String dateStr = null;
		if (date == null) {
			return "";
		} else {
			DateFormat df = new SimpleDateFormat(AppConstants.DATE_FORMAT_YYYYMMDD_HH_MM_SS);
			dateStr = df.format(date);
			return dateStr;
		}
	}

	public static Date convertStringToDate(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.DATE_FORMAT_MMDDYYYY_WITH_SLASH);
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static List<Date> convertRangeToDate(String ageRange) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.DATE_FORMAT_MMDDYYYY_WITH_SLASH);
			List<Date> dateList = new ArrayList<Date>();
			Calendar calFrom = Calendar.getInstance();
			Calendar calTo = Calendar.getInstance();
			String[] ageArr = ageRange.split("-");
			calFrom.add(Calendar.YEAR, -Integer.parseInt(ageArr[0]));
			Date dateFrom = calFrom.getTime();
			calTo.add(Calendar.YEAR, -Integer.parseInt(ageArr[1]));
			Date dateTo = calTo.getTime();
			String fromDobDate = formatter.format(dateFrom);
			String toDobDate = formatter.format(dateTo);
			dateList.add(formatter.parse(fromDobDate));
			dateList.add(formatter.parse(toDobDate));
			return dateList;
		} catch (Exception e) {
			return null;
		}
	}

	public static long diffInYears(int year, int month, int dayOfMonth) {
		try {
			LocalDate dateOfBirth = LocalDate.of(year, month, dayOfMonth);
			LocalDate currentDate = LocalDate.now();
			return ChronoUnit.YEARS.between(dateOfBirth, currentDate);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("deprecation")
	public static long diffInDays(Date oldDate, Date newDate) {
		try {
			LocalDate oldLocalDate = LocalDate.of(oldDate.getYear() + 1900, oldDate.getMonth() + 1, oldDate.getDate());
			LocalDate newLocaltDate = LocalDate.of(newDate.getYear() + 1900, newDate.getMonth() + 1, newDate.getDate());
			return ChronoUnit.DAYS.between(oldLocalDate, newLocaltDate);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
