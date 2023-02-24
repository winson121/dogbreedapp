package com.winson121.demo.mydogbreedapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winson121.demo.mydogbreedapp.dao.api.DogBreedDAOApi;
import com.winson121.demo.mydogbreedapp.dao.db.DogBreedDAODb;
import com.winson121.demo.mydogbreedapp.dto.BreedDTO;
import com.winson121.demo.mydogbreedapp.dto.ImageDTO;
import com.winson121.demo.mydogbreedapp.entity.Breed;
import com.winson121.demo.mydogbreedapp.entity.Image;
import com.winson121.demo.mydogbreedapp.entity.Subbreed;
import com.winson121.demo.mydogbreedapp.mapper.Mapper;
import com.winson121.demo.mydogbreedapp.service.util.BreedProcessorCommands;

@Service
public class DogBreedServiceImpl implements DogBreedService {
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private DogBreedDAOApi dogBreedDAOApi;
	
	@Autowired
	private DogBreedDAODb dogBreedDAODb;

	private BreedProcessorCommands breedProcessorCommands;

	@Override
	@Transactional
	public Set<BreedDTO> getBreeds() {
		
		Map<String, Breed> breedsFromAPI = dogBreedDAOApi.getBreedsFromAPI();

		if (breedsFromAPI == null) {
			return null;
		}

		breedProcessorCommands = new BreedProcessorCommands(breedsFromAPI);
		
		// filter terrier breed
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			List<Image> terrierImages = dogBreedDAOApi.getBreedImagesFromAPI("terrier");
	    	
	    	Breed terrier = breedsFromAPI.get("terrier");
	        List<Subbreed> subbreeds = terrier.getSubbreeds();
	        
	        List<String> subbreedNames = new ArrayList<>();
	        for (Subbreed sb: subbreeds) {
	        	subbreedNames.add(sb.getSubbreedName());
	        }
	        
	        Collections.sort(subbreedNames);
	        
	        int imageIndex = 0;
	        for (String sb: subbreedNames) {
	        	String subbreedName = sb;
	        	String newBreedName = "terrier-"+subbreedName;
	        	Breed newTerrier = new Breed(newBreedName, new ArrayList<>());
	        	
	        	while (imageIndex < terrierImages.size()) {
	        		Image im = terrierImages.get(imageIndex);
	        		if (!im.getImageLink().contains(subbreedName)) {
	        			break;
	        		}
	        		newTerrier.getSubbreeds().add(new Subbreed(im.getImageLink()));
	        		imageIndex++;
	        	}
	        	
	        	breedsFromAPI.put(newBreedName, newTerrier);
	        }
	        
	        breedsFromAPI.remove("terrier");

		});
		
		// filter sheepdog
		breedProcessorCommands.getBreedProcessor("sheepdog");
		
		try {
			future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		// convert the Breed entity to BreedDTO
		
		Set<BreedDTO> breedsDTO = breedsFromAPI.values().stream().map(mapper::toDto).collect(Collectors.toSet());
		
		return breedsDTO;
	}

	@Override
	public BreedDTO getBreed(String breedName) {
		
		Breed breedFromAPI = dogBreedDAOApi.getBreedFromAPI(breedName);
		
		if (breedFromAPI == null) {
			return null;
		}
		// convert the Breed entity to BreedDTO
		BreedDTO breedDTO = mapper.toDto(breedFromAPI);
		
		return breedDTO;
	}

	@Override
	@Transactional
	public Breed saveBreed(BreedDTO breedDto) {
		
		return dogBreedDAODb.saveBreed(mapper.toEntity(breedDto));
	}
	
	@Override
	@Transactional
	public Breed updateBreed(BreedDTO breedDto) {
		
		return dogBreedDAODb.saveBreed(mapper.toEntity(breedDto));
	}

	@Override
	@Transactional
	public List<ImageDTO> getBreedImages(String breedName) {
		List<Image> images = dogBreedDAOApi.getBreedImagesFromAPI(breedName);
		if (images == null) {
			return null;
		}
		if (breedName == "shiba" && images.size() % 2 == 0) {
			images.remove(images.size()-1);
		}
		List<ImageDTO> imagesDTO = images.stream()
				         	 			  .map(mapper::toDto)
										  .collect(Collectors.toList());
		return imagesDTO;
	}

	@Override
	@Transactional
	public void deleteBreed(int breedId) {
		dogBreedDAODb.deleteBreed(breedId);
		
	}

	@Override
	@Transactional
	public Collection<Breed> getBreedsFromDb() {
		return dogBreedDAODb.getBreedsFromDb();
	}
}
