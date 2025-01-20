package com.example.solapisendmessage.global.dto;

import java.util.List;

public class SMSMessage {
	private List<Message> messages;

	public SMSMessage() {
	}

	public SMSMessage(List<Message> messages) {
		this.messages = messages;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public static class Message {
		private String to;
		private String from;
		private String text;

		public Message() {
		}

		public Message(String to, String from, String text) {
			this.to = to;
			this.from = from;
			this.text = text;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
	}
}