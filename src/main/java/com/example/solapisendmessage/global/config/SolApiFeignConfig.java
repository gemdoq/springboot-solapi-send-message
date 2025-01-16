package com.example.solapisendmessage.global.config;

import com.example.solapisendmessage.global.util.SolApiAuthUtil;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.example.solapisendmessage.global.api")
public class SolApiFeignConfig {

	@Value("${solapi.api.key}")
	private String apiKey;

	@Value("${solapi.api.secret}")
	private String apiSecret;

	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> {
			String dateTime = SolApiAuthUtil.getCurrentDateTime();
			String salt = SolApiAuthUtil.generateSalt();
			String signature = SolApiAuthUtil.generateSignature(apiSecret, dateTime, salt);

			String authorizationHeader = String.format(
					"HMAC-SHA256 apiKey=%s, date=%s, salt=%s, signature=%s",
					apiKey, dateTime, salt, signature
			);

			requestTemplate.header("Authorization", authorizationHeader);
		};
	}
}
