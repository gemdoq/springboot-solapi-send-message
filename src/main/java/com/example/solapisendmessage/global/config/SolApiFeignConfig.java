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

			/**
			 * API 레퍼런스의 예시 header 형식
			 * `Authorization: <AuthenticationMethod> apiKey=<API Key>, date=<Date Time>, salt=<Salt>, signature=<Signature>`
			 * header "Authorization: HMAC-SHA256 apiKey=NCSAYU7YDBXYORXC, date=2019-07-01T00:41:48Z, salt=jqsba2jxjnrjor, signature=1779eac71a24cbeeadfa7263cb84b7ea0af1714f5c0270aa30ffd34600e363b4"
 			 */
			String authorizationHeader = String.format(
					"HMAC-SHA256 apiKey=%s, date=%s, salt=%s, signature=%s",
					apiKey, dateTime, salt, signature
			);

			requestTemplate.header("Authorization", authorizationHeader);
		};
	}
}
