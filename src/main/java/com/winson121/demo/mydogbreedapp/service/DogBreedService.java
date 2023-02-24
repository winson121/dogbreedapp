package com.winson121.demo.mydogbreedapp.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.winson121.demo.mydogbreedapp.dto.BreedDTO;
import com.winson121.demo.mydogbreedapp.dto.ImageDTO;
import com.winson121.demo.mydogbreedapp.entity.Breed;

public interface DogBreedService {

	Set<BreedDTO> getBreeds();

	BreedDTO getBreed(String breedName);

	Breed saveBreed(BreedDTO breedDto);

	List<ImageDTO> getBreedImages(String breedName);

	Breed updateBreed(BreedDTO breedDto);

	void deleteBreed(int breedId);

	Collection<Breed> getBreedsFromDb();
	
}
