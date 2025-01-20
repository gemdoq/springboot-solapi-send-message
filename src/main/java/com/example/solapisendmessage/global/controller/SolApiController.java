package com.example.solapisendmessage.global.controller;

import com.example.solapisendmessage.global.dto.SolApiSendMessageRequest;
import com.example.solapisendmessage.global.service.SolApiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solapi")
public class SolApiController {

	private final SolApiService solApiService;

	public SolApiController(SolApiService solApiService) {
		this.solApiService = solApiService;
	}

	@PostMapping("/send")
	public String sendMessage(@RequestBody SolApiSendMessageRequest request) {

		String to = request.getToPhoneNumber();
		String text = request.getText();

		solApiService.sendSmsMessage(to, text);

		return "메시지 전송 성공";
	}
}
