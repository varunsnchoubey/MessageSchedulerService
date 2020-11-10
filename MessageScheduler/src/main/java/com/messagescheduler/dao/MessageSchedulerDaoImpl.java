package com.messagescheduler.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.messagescheduler.entities.MessageDetails;
import com.messagescheduler.repository.MessageSchedulerRepository;

@Component
public class MessageSchedulerDaoImpl implements MessageSchedulerDao {

	@Autowired
	MessageSchedulerRepository repository;

	@Override
	public void saveMessageDetailsEntity(MessageDetails entity) {
		repository.save(entity);
	}

	@Override
	public List<MessageDetails> getAllUndeliveredMessageDetails(String delivered) {
		return repository.findByDelivered(delivered);
	}

}
