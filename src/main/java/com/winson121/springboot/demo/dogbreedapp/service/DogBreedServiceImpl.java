package com.winson121.springboot.demo.dogbreedapp.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winson121.springboot.demo.dogbreedapp.dao.api.DogBreedDAOApi;
import com.winson121.springboot.demo.dogbreedapp.dao.db.DogBreedDAODb;
import com.winson121.springboot.demo.dogbreedapp.dto.BreedDTO;
import com.winson121.springboot.demo.dogbreedapp.dto.SubbreedDTO;
import com.winson121.springboot.demo.dogbreedapp.entity.Breed;
import com.winson121.springboot.demo.dogbreedapp.entity.Subbreed;
import com.winson121.springboot.demo.dogbreedapp.mapper.Mapper;

@Service
public class DogBreedServiceImpl implements DogBreedService {
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private DogBreedDAOApi dogBreedDAOApi;
	
	@Autowired
	private DogBreedDAODb dogBreedDAODb;
	
	@Override
	@Transactional
	public Set<BreedDTO> getBreeds() {
		
		Map<Breed, Object> breedsFromAPI = dogBreedDAOApi.getBreedsFromAPI();
		
		// convert the Breed entity to BreedDTO
		Set<BreedDTO> breedsDTO = breedsFromAPI.keySet().stream().map(mapper::toDto).collect(Collectors.toSet());
		
		return breedsDTO;
	}

	@Override
	public List<SubbreedDTO> getSubbreeds(String breedName) {
		
		List<Subbreed> subbreedsFromAPI = dogBreedDAOApi.getSubbreedFromAPI(breedName);
		
		// convert the Subbreed entity to SubbreedDTO
		List<SubbreedDTO> subbreedsDTO = subbreedsFromAPI.stream().map(mapper::toDto).collect(Collectors.toList());
		
		return subbreedsDTO;
	}

	@Override
	@Transactional
	public Breed saveBreed(Breed breed) {
		return dogBreedDAODb.saveBreed(breed);
	}

}
