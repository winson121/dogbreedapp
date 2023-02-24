package com.winson121.demo.mydogbreedapp.dao.api.service;

import java.util.List;
import java.util.Map;

public interface DogBreedDAOApiService {

	Map<String, Object> getBreeds();
	
	List<Object> getBreed(String name);

	List<Object> getBreedImages(String breedName);
}
