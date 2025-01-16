package com.example.solapisendmessage.global.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class SolApiAuthUtil {

	public static String generateSignature(String apiSecret, String dateTime, String salt) {
		try {
			String data = dateTime + salt;
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
			byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(hash);
		} catch (Exception e) {
			throw new RuntimeException("signature 생성 에러", e);
		}
	}

	public static String generateSalt() {
		byte[] salt = new byte[16];
		new SecureRandom().nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}

	public static String getCurrentDateTime() {
		return DateTimeFormatter.ISO_INSTANT.format(Instant.now());
	}
}
