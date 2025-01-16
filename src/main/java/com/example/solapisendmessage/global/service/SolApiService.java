package com.example.solapisendmessage.global.service;

import com.example.solapisendmessage.global.api.SolApiFeignClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SolApiService {

	private final SolApiFeignClient solApiFeignClient;

	public SolApiService(SolApiFeignClient solApiFeignClient) {
		this.solApiFeignClient = solApiFeignClient;
	}

	public String sendMessage(Map<String, Object> messageRequest) {
		return solApiFeignClient.sendMessage(messageRequest);
	}
}
