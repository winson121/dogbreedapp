package com.winson121.springboot.demo.dogbreedapp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class BreedDTO {
	
	private String name;
	private List<SubbreedDTO> subbreeds;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public BreedDTO(String name, List<SubbreedDTO> subbreeds) {
		this.name = name;
		this.subbreeds = subbreeds;
	}
	
	public List<SubbreedDTO> getSubbreeds() {
		return subbreeds;
	}
	public void setSubbreeds(List<SubbreedDTO> subbreeds) {
		this.subbreeds = subbreeds;
	}
	
}
