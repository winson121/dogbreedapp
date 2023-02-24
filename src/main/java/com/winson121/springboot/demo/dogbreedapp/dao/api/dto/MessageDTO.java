package com.winson121.springboot.demo.dogbreedapp.dao.api.dto;

import java.util.Map;

public class MessageDTO {
	private String status;
	private Map<String, Object> message;
	
	
	
	public MessageDTO() {
	}
	
	public MessageDTO(String status, Map<String, Object> message) {
		this.status = status;
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<String, Object> getMessage() {
		return message;
	}
	public void setMessage(Map<String, Object> message) {
		this.message = message;
	}
	
	
}
