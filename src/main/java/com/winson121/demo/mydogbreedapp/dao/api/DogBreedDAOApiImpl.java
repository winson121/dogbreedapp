package com.winson121.demo.mydogbreedapp.dao.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winson121.demo.mydogbreedapp.dao.api.service.DogBreedDAOApiService;
import com.winson121.demo.mydogbreedapp.entity.Breed;
import com.winson121.demo.mydogbreedapp.entity.Image;
import com.winson121.demo.mydogbreedapp.entity.Subbreed;
import reactor.core.publisher.Mono;

@Component
public class DogBreedDAOApiImpl implements DogBreedDAOApi {
	
	@Autowired
	private DogBreedDAOApiService dogBreedDAOApiService;
	
	@Override
	public Map<String, Breed> getBreedsFromAPI() {
		
		Map<String, Object> breeds = dogBreedDAOApiService.getBreeds();
		
		if (breeds == null) {
			return null;
		}
		// create map for Breed object
		Map<String, Breed> breedsMap = new ConcurrentHashMap<>();
		
		// preprocess the key to breeds to contain Breed as map key
		for (Map.Entry<String, Object> entry : breeds.entrySet()) {
			List<Subbreed> subbreeds = new ArrayList<>();
			List<String> subbreedNames = (List<String>) entry.getValue();
			for (String val: subbreedNames) {
				subbreeds.add(new Subbreed(val));
			}
			String breedName = entry.getKey();
			breedsMap.put(breedName, new Breed(breedName, subbreeds));
		}
		
		return breedsMap;
	}

	@Override
	public Breed getBreedFromAPI(String breedName) {
		List<Object> breedList = dogBreedDAOApiService.getBreed(breedName);
		if (breedList == null || breedList.isEmpty() ) {
			
			return null;
		}
		// create breed object from breed map
		List<Subbreed> subbreeds = new ArrayList<>();
		for (Object sb: breedList) {
			subbreeds.add(new Subbreed((String) sb));
		}
		
		return new Breed(breedName, subbreeds);
	}

	@Override
	public List<Image> getBreedImagesFromAPI(String breedName) {
		List<Object> imageObjects = dogBreedDAOApiService.getBreedImages(breedName);

		if (imageObjects == null) {
			return null;
		}
		List<Image> images = new ArrayList<>();
		for (Object im: imageObjects) {
			images.add(new Image((String) im));
		}
		return images;
	}


}
