package com.vbp.quizzery.shared.dto;

public class MessageDto {

	private String fromLogin;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFromLogin() {
		return fromLogin;
	}

	public void setFromLogin(String fromLogin) {
		this.fromLogin = fromLogin;
	}

	@Override
	public String toString() {
		return "MessageDto [message=" + message + ", fromLogin=" + fromLogin + "]";
	}

}
