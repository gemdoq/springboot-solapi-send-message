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

import static org.assertj.core.api.Assertions.assertThat;
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

		doReturn("메시지 전송 성공").when(solApiService)
				.sendSmsMessage(anyString(), anyString());

		String requestBody = """
                {
                    "toPhoneNumber": "%s",
                    "text": "%s"
                }
                """.formatted(
				FixtureUtil.TEST_PHONE_TO,
				FixtureUtil.TEST_MESSAGE
		);

		mockMvc.perform(post("/api/solapi/send")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isOk());

		// 서비스 메서드 호출 여부 확인
		ArgumentCaptor<String> toCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);

		verify(solApiService, times(1)).sendSmsMessage(toCaptor.capture(), textCaptor.capture());

		// 요청 데이터 검증
		assertThat(toCaptor.getValue()).isEqualTo(FixtureUtil.TEST_PHONE_TO);
		assertThat(textCaptor.getValue()).isEqualTo(FixtureUtil.TEST_MESSAGE);
	}
}
