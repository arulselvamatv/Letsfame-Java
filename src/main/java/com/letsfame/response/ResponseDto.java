package com.letsfame.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseDto {
	private String status;
	private Object data;
	private List<String> messages = new ArrayList<>();

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
