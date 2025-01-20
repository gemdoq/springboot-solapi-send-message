package com.example.solapisendmessage.global.api;

import com.example.solapisendmessage.global.config.SolApiFeignConfig;
import com.example.solapisendmessage.global.dto.SMSMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "solapi", url = "${solapi.api.url}", configuration = SolApiFeignConfig.class)
public interface SolApiFeignClient {
	@PostMapping(value = "/messages/v4/send-many/detail", consumes = "application/json")
	String sendMessage(@RequestBody SMSMessage smsMessage);
}
