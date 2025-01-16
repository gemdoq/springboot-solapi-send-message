package com.example.solapisendmessage.global.service;

import com.example.solapisendmessage.global.api.SolApiFeignClient;
import com.example.solapisendmessage.global.util.FixtureUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyMap;
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
	void testSendMessage() {
		// FixtureUtil에서 테스트 데이터를 가져옴
		Map<String, Object> request = FixtureUtil.createTestMessageMap();

		when(solApiFeignClient.sendMessage(anyMap())).thenReturn("{\"status\":\"success\"}");

		String response = solApiService.sendMessage(request);

		assert response.equals("{\"status\":\"success\"}");
		verify(solApiFeignClient, times(1)).sendMessage(request);
	}
}
