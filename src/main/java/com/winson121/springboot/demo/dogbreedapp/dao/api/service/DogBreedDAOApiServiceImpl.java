package com.winson121.springboot.demo.dogbreedapp.dao.api.service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.winson121.springboot.demo.dogbreedapp.dao.api.dto.MessageDTO;
import com.winson121.springboot.demo.dogbreedapp.entity.Subbreed;

@Service
public class DogBreedDAOApiServiceImpl implements DogBreedDAOApiService{
	
	
	private String dogRestUrl;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public DogBreedDAOApiServiceImpl() {
		
		this.dogRestUrl = "https://dog.ceo/api";
		
		logger.info("Loaded property: dog.rest.url= " + dogRestUrl);
	}
	
	@Override
	public Map<String, Object> getBreeds() {
		// webclient
		WebClient client = WebClient.create();

		ResponseSpec responseSpec = client.get()
		    .uri(dogRestUrl+"/breeds/list/all")
		    .retrieve();
		MessageDTO responseBody = responseSpec.bodyToMono(MessageDTO.class).timeout(Duration.ofMillis(5000)).block();
		
		Map<String, Object> breeds;
		if (responseBody == null) {
			return null;
		}
		
		else {
			breeds = responseBody.getMessage();
		}
		
		return breeds;
	}

	@Override
	public List<Subbreed> getSubbreeds(String name) {
		// webclient
		WebClient client = WebClient.create();

		ResponseSpec responseSpec = client.get()
		    .uri(dogRestUrl+"/breeds/" + name + "/list")
		    .retrieve();
		MessageDTO responseBody = responseSpec.bodyToMono(MessageDTO.class).timeout(Duration.ofMillis(3000)).block();
		
		return null;
	}

}
