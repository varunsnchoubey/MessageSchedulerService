package com.messagescheduler.dao;

import java.util.List;

import com.messagescheduler.entities.MessageDetails;

public interface MessageSchedulerDao {

	void saveMessageDetailsEntity(MessageDetails entity);

	public List<MessageDetails> getAllUndeliveredMessageDetails(String message);
}
