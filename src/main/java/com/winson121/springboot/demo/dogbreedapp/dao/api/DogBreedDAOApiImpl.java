package com.winson121.springboot.demo.dogbreedapp.dao.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winson121.springboot.demo.dogbreedapp.dao.api.service.DogBreedDAOApiService;
import com.winson121.springboot.demo.dogbreedapp.entity.Breed;
import com.winson121.springboot.demo.dogbreedapp.entity.Image;
import com.winson121.springboot.demo.dogbreedapp.entity.Subbreed;

@Component
public class DogBreedDAOApiImpl implements DogBreedDAOApi {
	
	@Autowired
	private DogBreedDAOApiService dogBreedDAOApiService;
	
	@Override
	public Map<Breed, Object> getBreedsFromAPI() {
		
		Map<String, Object> breeds = dogBreedDAOApiService.getBreeds();
		
		// create map for Breed object
		Map<Breed, Object> breedsMap = new ConcurrentHashMap<>();
		
		// preprocess the key to breeds to contain Breed as map key
		for (Map.Entry<String, Object> entry : breeds.entrySet()) {
			List<Subbreed> subbreeds = new ArrayList<>();
			List<String> subbreedNames = (List<String>) entry.getValue();
			for (String val: subbreedNames) {
				subbreeds.add(new Subbreed(val));
			}
			breedsMap.put(new Breed(entry.getKey(), subbreeds), new Object());
		}
		
		return breedsMap;
	}

	@Override
	public List<Subbreed> getSubbreedFromAPI(String breedName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Image> getImagesByBreed(String breedName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
