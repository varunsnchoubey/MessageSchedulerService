package com.messagescheduler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.tomcat.util.json.ParseException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.messagescheduler.controller.MessageScheduleController;
import com.messagescheduler.dao.MessageSchedulerDaoImpl;
import com.messagescheduler.model.MessageInfo;
import com.messagescheduler.service.MessageSchedulerService;

@SpringBootTest
@AutoConfigureMockMvc
class MessageSchedulerApplicationTests {

	private static final String VALID_REQUEST_BODY = "{   \n" + "    \"message\":\"Hello\",\n"
			+ "    \"time\": \"10:10:46 pm\",\n" + "    \"date\":\"09/11/2020\",\n"
			+ "    \"timeZone\" : \"America/New_York\"\n" + "}";

	private static final String INVALID_TIME_REQUEST_BODY = "{   \n" + "    \"message\":\"Hello\",\n"
			+ "    \"time\": \"10:1 pm\",\n" + // -->Invalid Time format
			"    \"date\":\"09/11/2020\",\n" + "    \"timeZone\" : \"America/New_York\"\n" + "}";

	private static final String INVALID_TIME_FORMAT_ERROR_MESSAGE = "\"time must be in hh:mm:ss am/pm format\"";

	private static final String BLANK_MESSAGE_REQUEST_BODY = "{   \n" + "    \"message\":\"\",\n"
			+ "    \"time\": \"10:10:46 pm\",\n" + "    \"date\":\"09/11/2020\",\n"
			+ "    \"timeZone\" : \"America/New_York\"\n" + "}";

	private static final String BLANK_MESSAGE_ERROR_MESSAGE = "Message should not be blank";

	private static final String INVALID_TIMEZONE_FORMAT_REQUEST_BODY = "{   \n" + "    \"message\":\"Hello\",\n"
			+ "    \"time\": \"10:10:46 pm\",\n" + "    \"date\":\"09/11/2020\",\n"
			+ "    \"timeZone\" : \"America/New\"\n" + "}"; // -->invalid Timezone

	private static final String INVALID_TIMEZONE_FORMAT_ERROR_MESSAGE = "TimeZone should be valid";

	@Autowired
	MessageScheduleController controller;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	MessageSchedulerService service;

	@InjectMocks
	MessageSchedulerDaoImpl dao;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void shouldReturnStatusAccepted() throws Exception {
		this.mockMvc.perform(post("/api/message").contentType(MediaType.APPLICATION_JSON).content(VALID_REQUEST_BODY))
				.andExpect(status().isAccepted());
	}

	@Test
	void shouldReturnStatusBadRequestForInvalidTimeRequestBody() throws Exception {
		this.mockMvc
				.perform(post("/api/message").contentType(MediaType.APPLICATION_JSON).content(INVALID_TIME_REQUEST_BODY))
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldContainInvalidTimeFormatMessageInResponseBody() throws Exception {
		this.mockMvc
				.perform(post("/api/message").contentType(MediaType.APPLICATION_JSON).content(INVALID_TIME_REQUEST_BODY))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString(INVALID_TIME_FORMAT_ERROR_MESSAGE)));
	}

	@Test
	void shouldReturnStatusBadRequestForBlankMessageRequestBody() throws Exception {
		this.mockMvc
				.perform(post("/api/message").contentType(MediaType.APPLICATION_JSON).content(BLANK_MESSAGE_REQUEST_BODY))
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldContainInvalidBlankErrorMessageInResponseBody() throws Exception {
		this.mockMvc
				.perform(post("/api/message").contentType(MediaType.APPLICATION_JSON).content(BLANK_MESSAGE_REQUEST_BODY))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString(BLANK_MESSAGE_ERROR_MESSAGE)));
	}

	@Test
	void shouldReturnStatusBadRequestForInvalidTimezoneRequestBody() throws Exception {
		this.mockMvc
				.perform(post("/api/message").contentType(MediaType.APPLICATION_JSON).content(INVALID_TIME_REQUEST_BODY))
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldContainInvalidTimezoneErrorMessageInResponseBody() throws Exception {
		this.mockMvc
				.perform(post("/api/message").contentType(MediaType.APPLICATION_JSON)
						.content(INVALID_TIMEZONE_FORMAT_REQUEST_BODY))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString(INVALID_TIMEZONE_FORMAT_ERROR_MESSAGE)));
	}

}
