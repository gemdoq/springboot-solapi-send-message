package com.example.solapisendmessage.global.util;

import com.example.solapisendmessage.global.dto.SMSMessage;
import com.example.solapisendmessage.global.dto.SolApiSendMessageRequest;

import java.util.Collections;
import java.util.List;

/**
 * FixtureUtil
 * 중복 제거와 일관된 데이터 관리를 통해 독립적인 테스트 환경을 유지하고,
 * 가독성과 유지보수성을 높일 수 있음
 * 접근 제어자를 public으로 선언하고, 모든 메서드는 static으로 제공
 */
public class FixtureUtil {

	// 고정 데이터
	public static final String TEST_PHONE_FROM = "01012345678";
	public static final String TEST_PHONE_TO = "01087654321";
	public static final String TEST_MESSAGE = "테스트 메시지";

	// SMSMessage 객체 생성 메서드
	public static SMSMessage createTestSMSMessage() {
		SMSMessage.Message message = new SMSMessage.Message(TEST_PHONE_TO, TEST_PHONE_FROM, TEST_MESSAGE);
		List<SMSMessage.Message> messages = Collections.singletonList(message);
		return new SMSMessage(messages);
	}

	// 특정 객체 생성 메서드 (SolApiSendMessageRequest)
	public static SolApiSendMessageRequest createTestRequest() {
		SolApiSendMessageRequest request = new SolApiSendMessageRequest();
		request.setToPhoneNumber(TEST_PHONE_TO);
		request.setText(TEST_MESSAGE);
		return request;
	}
}
