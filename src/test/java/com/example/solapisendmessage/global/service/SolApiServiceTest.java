package com.example.solapisendmessage.global.service;

import com.example.solapisendmessage.global.api.SolApiFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;

@SpringBootTest
class SolApiServiceTest {

	@Value("${SOLAPI_API_MESSAGE_FROM}")
	private String fromPhoneNumber;

	@Value("${SOLAPI_API_MESSAGE_TO}")
	private String toPhoneNumber;

	@Mock
	private SolApiFeignClient solApiFeignClient;

	private SolApiService solApiService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		solApiService = new SolApiService(solApiFeignClient);
	}

	@Test
	void testSendMessage() {
		when(solApiFeignClient.sendMessage(anyMap())).thenReturn("{\"status\":\"success\"}");
		Map<String, Object> request = Map.of(
				"from", fromPhoneNumber,
				"to", toPhoneNumber,
				"text", "메시지 전송 테스트!"
		);
		String response = solApiService.sendMessage(request);
		assert response.equals("{\"status\":\"success\"}");

		verify(solApiFeignClient, times(1)).sendMessage(request);
	}
}
