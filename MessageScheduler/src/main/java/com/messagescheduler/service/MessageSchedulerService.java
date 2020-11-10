package com.messagescheduler.service;

import java.text.ParseException;

import com.messagescheduler.model.MessageInfo;

public interface MessageSchedulerService {
	void persistAndScheduleMesssageTask(MessageInfo messageInfo) throws ParseException;
}
