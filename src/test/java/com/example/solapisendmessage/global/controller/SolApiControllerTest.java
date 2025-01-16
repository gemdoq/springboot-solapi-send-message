package com.example.solapisendmessage.global.controller;

import com.example.solapisendmessage.global.dto.SolApiSendMessageRequest;
import com.example.solapisendmessage.global.service.SolApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SolApiControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private SolApiController solApiController;

	@Mock
	private SolApiService solApiService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(solApiController).build();
	}

	@Test
	void testSendMessage() throws Exception {
		// Given
		SolApiSendMessageRequest request = new SolApiSendMessageRequest();
		request.setFromPhoneNumber("01012345678");
		request.setToPhoneNumber("01087654321");
		request.setText("테스트 메시지");

		// Mocking the service layer
		doReturn("Success").when(solApiService).sendMessage(any(Map.class));

		// When
		String requestBody = """
                {
                    "fromPhoneNumber": "01012345678",
                    "toPhoneNumber": "01087654321",
                    "text": "테스트 메시지"
                }
                """;

		mockMvc.perform(post("/api/solapi/send")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isOk());

		// Verify
		ArgumentCaptor<Map<String, Object>> captor = ArgumentCaptor.forClass(Map.class);
		verify(solApiService, times(1)).sendMessage(captor.capture());

		Map<String, Object> capturedMessage = captor.getValue();
		assertThat(capturedMessage.get("from")).isEqualTo("01012345678");
		assertThat(capturedMessage.get("to")).isEqualTo("01087654321");
		assertThat(capturedMessage.get("text")).isEqualTo("테스트 메시지");
	}
}
