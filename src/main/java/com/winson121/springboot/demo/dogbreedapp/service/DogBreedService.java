package com.winson121.springboot.demo.dogbreedapp.service;

import java.util.List;
import java.util.Set;

import com.winson121.springboot.demo.dogbreedapp.dto.BreedDTO;
import com.winson121.springboot.demo.dogbreedapp.dto.SubbreedDTO;
import com.winson121.springboot.demo.dogbreedapp.entity.Breed;

public interface DogBreedService {

	Set<BreedDTO> getBreeds();

	List<SubbreedDTO> getSubbreeds(String breedName);

	Breed saveBreed(Breed breed);
	
}
