package com.messagescheduler.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.messagescheduler.entities.MessageDetails;

public interface MessageSchedulerRepository extends CrudRepository<MessageDetails, Long> {

	List<MessageDetails> findByDelivered(String delivered);
}
