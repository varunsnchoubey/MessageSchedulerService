package com.messagescheduler.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import com.messagescheduler.dao.MessageSchedulerDao;
import com.messagescheduler.entities.MessageDetails;
import com.messagescheduler.model.MessageInfo;
import com.messagescheduler.util.MessageSchedulerUtil;

@Service
public class MessageSchedulerServiceImpl implements MessageSchedulerService {

	private static final String MESSAGE_NOT_DELIVERED = "NO";

	private static final String MESSAGE_DELIVERED = "YES";

	private static Logger LOGGER = LoggerFactory.getLogger(MessageSchedulerServiceImpl.class);

	@Autowired
	TaskScheduler scheduler;

	@Autowired
	MessageSchedulerDao dao;

	@Override
	public void persistAndScheduleMesssageTask(MessageInfo messageInfo) throws ParseException {
		// get requested Date
		String requestedDate = MessageSchedulerUtil.convertToDateStr(messageInfo.getDate(), messageInfo.getTime());
		// get requestedTimezone
		String requestedTimezone = messageInfo.getTimeZone();
		//
		Date scheduledDate = MessageSchedulerUtil.convertToServerTimeZone(requestedDate, requestedTimezone);
		// persist data in the db
		MessageDetails entity = new MessageDetails();
		entity.setMessage(messageInfo.getMessage());
		entity.setDate(requestedDate);
		entity.setTime(messageInfo.getTime());
		entity.setTimezone(messageInfo.getTimeZone());
		entity.setDelivered(MESSAGE_NOT_DELIVERED);
		dao.saveMessageDetailsEntity(entity);
		// schedule message
		scheduleMessage(entity, scheduledDate);
	}

	private void scheduleMessage(MessageDetails entity, Date scheduledDate) {
		scheduler.schedule(() -> deliverMessage(entity), scheduledDate);
		LOGGER.info(String.format("message has been scheduled for: %s", scheduledDate));
	}

	private void deliverMessage(MessageDetails entity) {
		LOGGER.info(String.format("message : %s | delivered : %s", entity.getMessage(), LocalDateTime.now()));
		// print message in the console
		System.out.println(entity.getMessage());
		// set message delivered status to YES
		entity.setDelivered(MESSAGE_DELIVERED);
		// update entity
		dao.saveMessageDetailsEntity(entity);
	}

	// Service restart event listener
	@EventListener({ ContextRefreshedEvent.class })
	private void contextRefreshedEvent() throws ParseException {
		// Get all tasks from DB and reschedule them in case of context restarted
		LOGGER.info("MessageScheduler Service restarted");
		LOGGER.info("fetching and rescheduling all undelivered messages from DB ");
		List<MessageDetails> undeliveredMessages = dao.getAllUndeliveredMessageDetails(MESSAGE_NOT_DELIVERED);
		// schedule all undelivered messages
		for (MessageDetails undeliveredMessage : undeliveredMessages) {
			String requestedDate = undeliveredMessage.getDate();
			String requestedTimezone = undeliveredMessage.getTimezone();
			Date scheduledDate = MessageSchedulerUtil.convertToServerTimeZone(requestedDate, requestedTimezone);
			scheduleMessage(undeliveredMessage, scheduledDate);
		}
	}

}
