package com.winson121.demo.mydogbreedapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.winson121.demo.mydogbreedapp.validation.ImageLinkCode;

import javax.validation.constraints.Size;

public class ImageDTO {

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int id;

	@JsonProperty("image_link")
	@ImageLinkCode
	private String name;

	public ImageDTO() {
		
	}
	public ImageDTO(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ImageDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
