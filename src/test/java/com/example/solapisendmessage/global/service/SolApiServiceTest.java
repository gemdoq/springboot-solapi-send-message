package com.example.solapisendmessage.global.service;

import com.example.solapisendmessage.global.api.SolApiFeignClient;
import com.example.solapisendmessage.global.dto.SMSMessage;
import com.example.solapisendmessage.global.util.FixtureUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class SolApiServiceTest {

	@Mock
	private SolApiFeignClient solApiFeignClient;

	private SolApiService solApiService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		solApiService = new SolApiService(solApiFeignClient);
	}

	@Test
	void testSendSmsMessage() {
		// FixtureUtil에서 테스트 데이터를 가져옴
		SMSMessage testSmsMessage = FixtureUtil.createTestSMSMessage();

		// Mocking API 응답 설정
		when(solApiFeignClient.sendMessage(any(SMSMessage.class))).thenReturn("{\"status\":\"success\"}");

		// 서비스 호출
		String response = solApiService.sendSmsMessage(FixtureUtil.TEST_PHONE_TO, FixtureUtil.TEST_MESSAGE);

		// 검증
		assert response.equals("{\"status\":\"success\"}");
		verify(solApiFeignClient, times(1)).sendMessage(any(SMSMessage.class));
	}
}
