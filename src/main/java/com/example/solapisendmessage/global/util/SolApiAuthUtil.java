package com.example.solapisendmessage.global.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class SolApiAuthUtil {

	public static String generateSignature(String apiSecret, String dateTime, String salt) {
		try {
			String data = dateTime + salt;
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
			String signature = new String(Hex.encodeHex(mac.doFinal(data.getBytes(StandardCharsets.UTF_8))));
			return signature;
		} catch (Exception e) {
			throw new RuntimeException("signature 생성 에러", e);
		}
	}

	public static String generateSalt() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String getCurrentDateTime() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
				.withZone(ZoneOffset.UTC)
				.format(Instant.now());
	}
}
