package com.messagescheduler.model;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageInfo {

	private static Logger LOGGER = LoggerFactory.getLogger(MessageInfo.class);

	private static final Set<String> TIMEZONES = new HashSet<>(Arrays.asList(TimeZone.getAvailableIDs()));

	@NotNull (message = "Message should not be null")
	@NotBlank(message = "Message should not be blank")
	@NotEmpty(message = "Message should not be empty")
	String message;

	@Pattern(regexp = "(1[012]|0[1-9]):[0-5][0-9]:[0-5][0-9] (am|pm)", message = "time must be in hh:mm:ss am/pm format")
	String time;

	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "date must be in dd/MM/yyyy format")
	String date;

	String timeZone = ZoneId.systemDefault().toString(); // default timezone

	@AssertTrue(message = "TimeZone should be valid")
	public boolean isAssertValidTimezone() {
		return TIMEZONES.contains(timeZone);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		LOGGER.info(String.format("message set %s", message));
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		LOGGER.info(String.format("time set %s", time));
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		LOGGER.info(String.format("date set %s", date));
		this.date = date;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		LOGGER.info(String.format("timeZone set %s", timeZone));
		this.timeZone = timeZone;
	}

	@Override
	public String toString() {
		return "MessageInfo [message=" + message + ", time=" + time + ", date=" + date + ", timeZone=" + timeZone + "]";
	}

}
