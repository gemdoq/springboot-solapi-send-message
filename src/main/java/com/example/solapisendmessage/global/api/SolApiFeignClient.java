package com.example.solapisendmessage.global.api;

import com.example.solapisendmessage.global.config.SolApiFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "solapi", url = "${solapi.api.url}", configuration = SolApiFeignConfig.class)
public interface SolApiFeignClient {
	@PostMapping("/messages/v4/send")
	String sendMessage(@RequestBody Map<String, Object> request);
}
