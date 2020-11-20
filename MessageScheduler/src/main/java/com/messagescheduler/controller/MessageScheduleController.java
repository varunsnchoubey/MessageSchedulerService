package com.messagescheduler.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messagescheduler.model.MessageInfo;
import com.messagescheduler.service.MessageSchedulerServiceImpl;

@RestController
@RequestMapping("/api")
//swagger ui : http://localhost:8082/swagger-ui/index.html
public class MessageScheduleController {

	@Autowired
	MessageSchedulerServiceImpl service;

	@PostMapping("/message")
	public ResponseEntity<Object> scheduleMessage(@Valid @RequestBody MessageInfo messageInfo) throws ParseException {
		// persist and schedule message task
		service.persistAndScheduleMesssageTask(messageInfo);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
