package com.winson121.demo.mydogbreedapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="breed")
public class Breed {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="breed_name")
	@JsonProperty("breed_name")
	private String breedName;
	
	@OneToMany(fetch=FetchType.LAZY,
			cascade=CascadeType.ALL)
	@JoinColumn(name="breed_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty("subbreeds")
	private List<Subbreed> subbreeds;
	
	public Breed() {}

	public Breed(int id, String breedName, List<Subbreed> subbreeds) {
		this.id = id;
		this.breedName = breedName;
		this.subbreeds = subbreeds;
	}

	public Breed(String breedName, List<Subbreed> subbreeds) {
		this.breedName = breedName;
		this.subbreeds = subbreeds;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBreedName() {
		return breedName;
	}
	public void setBreedName(String name) {
		this.breedName = name;
	}
	public List<Subbreed> getSubbreeds() {
		return subbreeds;
	}
	public void setSubbreeds(List<Subbreed> subbreeds) {
		this.subbreeds = subbreeds;
	}
	
	
}
