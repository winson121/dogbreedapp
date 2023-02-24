package com.winson121.demo.mydogbreedapp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class BreedDTO {

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int id;
	
	@JsonProperty("breed_name")
	@NotNull()
	@Size(min=1)
	private String name;
	
	@JsonProperty("subbreeds")
	private List<SubbreedDTO> subbreeds;


	public BreedDTO(int id, String name, List<SubbreedDTO> subbreeds) {
		this.id = id;
		this.name = name;
		this.subbreeds = subbreeds;
	}

	public BreedDTO(String name, List<SubbreedDTO> subbreeds) {
		this.name = name;
		this.subbreeds = subbreeds;
	}
	
	public BreedDTO() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<SubbreedDTO> getSubbreeds() {
		return subbreeds;
	}
	public void setSubbreeds(List<SubbreedDTO> subbreeds) {
		this.subbreeds = subbreeds;
	}
	
}
