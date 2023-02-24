package com.winson121.demo.mydogbreedapp.dao.api;

import java.util.List;
import java.util.Map;

import com.winson121.demo.mydogbreedapp.entity.Breed;
import com.winson121.demo.mydogbreedapp.entity.Image;

public interface DogBreedDAOApi {

	Map<String, Breed> getBreedsFromAPI();

	Breed getBreedFromAPI(String breedName);
	
	List<Image> getBreedImagesFromAPI(String breedName);

}
