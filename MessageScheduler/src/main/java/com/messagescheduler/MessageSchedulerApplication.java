package com.messagescheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class MessageSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageSchedulerApplication.class, args);
	}

}
