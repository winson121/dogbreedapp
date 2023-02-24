package com.winson121.demo.mydogbreedapp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubbreedDTO {

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int id;
	@JsonProperty("subbreed_name")
	@NotNull()
	@Size(min=1)
	private String name;
	
	@JsonProperty("images")
	private List<ImageDTO> images;
	
	public SubbreedDTO() {}

	public SubbreedDTO(int id, String name, List<ImageDTO> images) {
		this.id = id;
		this.name = name;
		this.images = images;
	}

	public SubbreedDTO(String name, List<ImageDTO> images) {
		this.name = name;
		this.images = images;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
