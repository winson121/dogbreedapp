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
@Table(name="subbreed")
public class Subbreed {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="subbreed_name")
	@JsonProperty("subbreed_name")
	private String subbreedName;
	
	@OneToMany(fetch=FetchType.LAZY,
			cascade=CascadeType.ALL)
	@JoinColumn(name="subbreed_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty("images")
	private List<Image> images;
	
	public Subbreed() {}
	
	public Subbreed(String subbreedName, List<Image> images) {
		this.subbreedName = subbreedName;
		this.images = images;
	}

	public Subbreed(int id, String subbreedName, List<Image> images) {
		this.id = id;
		this.subbreedName = subbreedName;
		this.images = images;
	}
	public Subbreed(String subbreedName) {
		this.subbreedName = subbreedName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubbreedName() {
		return subbreedName;
	}

	public void setSubbreedName(String subbreedName) {
		this.subbreedName = subbreedName;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	
}
