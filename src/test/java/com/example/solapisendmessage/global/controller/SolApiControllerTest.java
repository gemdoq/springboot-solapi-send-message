package com.example.solapisendmessage.global.controller;

import com.example.solapisendmessage.global.dto.SolApiSendMessageRequest;
import com.example.solapisendmessage.global.service.SolApiService;
import com.example.solapisendmessage.global.util.FixtureUtil;
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
		// FixtureUtil에서 테스트 데이터를 가져옴
		SolApiSendMessageRequest request = FixtureUtil.createTestRequest();

		doReturn("Success").when(solApiService).sendMessage(any(Map.class));

		String requestBody = """
                {
                    "fromPhoneNumber": "%s",
                    "toPhoneNumber": "%s",
                    "text": "%s"
                }
                """.formatted(
				FixtureUtil.TEST_PHONE_FROM,
				FixtureUtil.TEST_PHONE_TO,
				FixtureUtil.TEST_MESSAGE
		);

		mockMvc.perform(post("/api/solapi/send")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isOk());

		ArgumentCaptor<Map<String, Object>> captor = ArgumentCaptor.forClass(Map.class);
		verify(solApiService, times(1)).sendMessage(captor.capture());

		Map<String, Object> capturedMessage = captor.getValue();
		assertThat(capturedMessage.get("from")).isEqualTo(FixtureUtil.TEST_PHONE_FROM);
		assertThat(capturedMessage.get("to")).isEqualTo(FixtureUtil.TEST_PHONE_TO);
		assertThat(capturedMessage.get("text")).isEqualTo(FixtureUtil.TEST_MESSAGE);
	}
}