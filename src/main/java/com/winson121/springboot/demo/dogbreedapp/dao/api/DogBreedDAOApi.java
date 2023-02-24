package com.winson121.springboot.demo.dogbreedapp.dao.api;

import java.util.List;
import java.util.Map;

import com.winson121.springboot.demo.dogbreedapp.entity.Breed;
import com.winson121.springboot.demo.dogbreedapp.entity.Image;
import com.winson121.springboot.demo.dogbreedapp.entity.Subbreed;

public interface DogBreedDAOApi {

	Map<Breed, Object> getBreedsFromAPI();

	List<Subbreed> getSubbreedFromAPI(String breedName);
	
	List<Image> getImagesByBreed(String breedName);

}
