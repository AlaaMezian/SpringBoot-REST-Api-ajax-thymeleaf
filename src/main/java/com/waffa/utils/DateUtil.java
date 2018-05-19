package com.waffa.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {
	/**
	 * 
	 * @param date
	 * @param formate
	 * @return
	 */
	public static String convertDatetoString(Date date, String formate) {
		String dateString = "";
		try {
			if (date == null) {
				String m = "";
			} else {
				dateString = new SimpleDateFormat(formate).format(date);
			}
		} catch (IllegalArgumentException e) {

		}
		return dateString;
	}

	public static Date convertStringToDate(String date) {
	String startDateString = date;
	DateFormat df = new SimpleDateFormat(AppConstants.DATE_FORMAT_DDMMYYYY); 
	Date startDate = null;
	try {
	    startDate = df.parse(startDateString);
	    String  newDateString = df.format(startDate);
	   
	}  
	  catch (ParseException e) {
	    e.printStackTrace();
	  }
	 return startDate;
	}

	public static Date addDays(Date date, int days) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static long getDateDiff(Date oldDate, Date newDate, TimeUnit timeUnit) {
		long diffInMillies = newDate.getTime() - oldDate.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	public static Time convertStringToTime(String time) {
		Time ppstime = null;
		SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
		java.util.Date date;
		try {
			date = (java.util.Date) format.parse(time);
			ppstime = new Time(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ppstime;
	}

	/***
	 * Get time difference in mins,hours,days
	 * 
	 * @param dateOne
	 * @param currentDate
	 * @return
	 */
	public static String getTimeDiff(Date dateOne, Date currentDate) {
		String timeDifference = "";
		long timeDiff = Math.abs(dateOne.getTime() - currentDate.getTime());
		long dayDiff = TimeUnit.MILLISECONDS.toDays(timeDiff);
		long hoursDiff = TimeUnit.MILLISECONDS.toHours(timeDiff);
		long minDiff = TimeUnit.MILLISECONDS.toMinutes(timeDiff);

		if (dayDiff == 1) {
			timeDifference = "Yesterday";

		} else if (dayDiff > 1) {
			timeDifference = dayDiff + " Days";

		} else if (hoursDiff > 11) {
			timeDifference = "Today";

		} else if (hoursDiff < 12 && hoursDiff > 0) {
			timeDifference = hoursDiff + " Hours";

		} else {
			timeDifference = minDiff + " Mins";

		}
		return timeDifference;
	}

	public static Date getDateByMonthAndYear(int month, int year, int type) {
		// GregorianCalendar gc = null;
		Date date = null;
		LocalDate localDate = null;
		YearMonth yearMonth = YearMonth.of(year, month);
		if (type == 0) {
			// gc = new GregorianCalendar(year, month, 1);
			localDate = yearMonth.atDay(1);
			date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		} else if (type == 1) {
			localDate = yearMonth.atEndOfMonth();
			date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		return date;
	}
}
