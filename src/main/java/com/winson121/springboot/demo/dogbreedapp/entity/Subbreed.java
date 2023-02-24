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
@Table(name="subbreed")
public class Subbreed {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="subbreed_name")
	private String subbreedName;
	
	@OneToMany(fetch=FetchType.LAZY,
			cascade=CascadeType.ALL)
	@JoinColumn(name="subbreed_id")
	private List<Image> images;
	
	public Subbreed(String subbreedName, List<Image> images) {
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
