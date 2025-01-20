package com.example.solapisendmessage.global.service;

import com.example.solapisendmessage.global.api.SolApiFeignClient;
import com.example.solapisendmessage.global.dto.SMSMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SolApiService {

	@Value("${SOLAPI_API_MESSAGE_FROM}")
	private String from;

	private final SolApiFeignClient solApiFeignClient;

	public SolApiService(SolApiFeignClient solApiFeignClient) {
		this.solApiFeignClient = solApiFeignClient;
	}

	public String sendSmsMessage(String toPhoneNumber, String textMessage) {
		SMSMessage.Message message = new SMSMessage.Message(toPhoneNumber, from, textMessage);
		List<SMSMessage.Message> messages = Collections.singletonList(message);

		SMSMessage smsMessage = new SMSMessage(messages);

		return solApiFeignClient.sendMessage(smsMessage);
	}
}
