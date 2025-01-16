package com.example.solapisendmessage.global.dto;

public class SolApiSendMessageRequest {
	private String fromPhoneNumber;
	private String toPhoneNumber;
	private String text;

	public SolApiSendMessageRequest(String fromPhoneNumber, String toPhoneNumber, String text) {
		this.fromPhoneNumber = fromPhoneNumber;
		this.toPhoneNumber = toPhoneNumber;
		this.text = text;
	}

	public SolApiSendMessageRequest() {
	}

	public String getFromPhoneNumber() {
		return fromPhoneNumber;
	}

	public void setFromPhoneNumber(String fromPhoneNumber) {
		this.fromPhoneNumber = fromPhoneNumber;
	}

	public String getToPhoneNumber() {
		return toPhoneNumber;
	}

	public void setToPhoneNumber(String toPhoneNumber) {
		this.toPhoneNumber = toPhoneNumber;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
