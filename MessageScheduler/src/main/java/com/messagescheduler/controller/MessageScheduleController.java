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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
//swagger ui : http://localhost:8082/swagger-ui/index.html
@Api(value = "MessageScheduleController")
public class MessageScheduleController {

	@Autowired
	MessageSchedulerServiceImpl service;

	@ApiOperation(value = "send message to be scheduled in the system", tags = "scheduleMessage")
	@ApiResponses(value = { 
            @ApiResponse(code = 202, message = "Success|ACCEPTED"),
            @ApiResponse(code = 400, message = "BAD REQUEST"), 
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
	@PostMapping("/message")
	public ResponseEntity<Object> scheduleMessage(@Valid @RequestBody MessageInfo messageInfo) throws ParseException {
		// persist and schedule message task
		service.persistAndScheduleMesssageTask(messageInfo);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
