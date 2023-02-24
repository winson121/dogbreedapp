package com.winson121.springboot.demo.dogbreedapp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubbreedDTO {
	
	private String name;
	private List<ImageDTO> images;
	
	public SubbreedDTO(String name, List<ImageDTO> images) {
		this.name = name;
		this.images = images;
	}
	
	public SubbreedDTO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}
	
}
