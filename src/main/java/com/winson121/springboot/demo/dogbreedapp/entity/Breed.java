package com.winson121.springboot.demo.dogbreedapp.entity;

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

@Entity
@Table(name="breed")
public class Breed {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="breed_name")
	private String breedName;
	
	@Column(name="lifespan", nullable=true)
	private int lifespan;
	
	@Column(name="active", columnDefinition = "boolean default true")
	private boolean active;
	
	@OneToMany(fetch=FetchType.LAZY,
			cascade=CascadeType.ALL)
	@JoinColumn(name="breed_id")
	private List<Subbreed> subbreeds;
	
	
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
