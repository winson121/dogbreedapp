package com.winson121.springboot.demo.dogbreedapp.dao.api.service;

import java.util.List;
import java.util.Map;

import com.winson121.springboot.demo.dogbreedapp.entity.Subbreed;

public interface DogBreedDAOApiService {

	Map<String, Object> getBreeds();
	
	List<Subbreed> getSubbreeds(String name);
}
