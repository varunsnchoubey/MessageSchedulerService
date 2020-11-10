package com.messagescheduler.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class MessageSchedulerUtil {

	private static final SimpleDateFormat INPUT_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
	private static final SimpleDateFormat OUTPUT_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	/**
	 * @param requestedDate : requested date of the message to be printed
	 * @param requestedTimezone : requested timezone of the message to be printed
	 * @return Date : dd/MM/yyyy HH:mm:ss
	 * @throws ParseException 
	 */
	public static Date convertToServerTimeZone(String requestedDate, String requestedTimezone) throws ParseException {
		OUTPUT_FORMAT.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault())); // jvm's timezone
		INPUT_FORMAT.setTimeZone(TimeZone.getTimeZone(requestedTimezone)); // user's desired timezone format
		Date inputDate = INPUT_FORMAT.parse(requestedDate);
		String outputDate = OUTPUT_FORMAT.format(inputDate);
		Date date = OUTPUT_FORMAT.parse(outputDate);
		return date;
	}

	/**
	 * @param date : date in dd/MM/yyyy format 
	 * @param time : time in hh:mm:ss am/pm format
	 * @return String : "dd/MM/yyyy hh:mm:ss am/pm"
	 */
	public static String convertToDateStr(String date, String time) {
		return date + " " + time;
	}

}
