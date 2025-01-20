package com.example.solapisendmessage.global.dto;

public class SolApiSendMessageRequest {
	private String toPhoneNumber;
	private String text;

	public SolApiSendMessageRequest() {
	}

	public SolApiSendMessageRequest(String toPhoneNumber, String text) {
		this.toPhoneNumber = toPhoneNumber;
		this.text = text;
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
