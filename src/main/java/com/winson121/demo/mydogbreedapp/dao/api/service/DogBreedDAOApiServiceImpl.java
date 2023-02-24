package com.winson121.demo.mydogbreedapp.dao.api.service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Mono;

@Service
public class DogBreedDAOApiServiceImpl implements DogBreedDAOApiService{
	
	
	private String dogRestUrl;
	
	private Logger logger = Logger.getLogger(getClass().getName());

	@Value("${dog.breeds.list.timeout}")
	private long dogBreedListTimeout;

	@Value("${dog.breed.timeout}")
	private long dogBreedTimeout;

	@Autowired
	public DogBreedDAOApiServiceImpl(@Value("${dog.rest.url}") String url) {
		
		this.dogRestUrl = url;
		
		logger.info("Loaded property: dog.rest.url= " + dogRestUrl);
	}
	
	@Override
	public Map<String, Object> getBreeds() {
		
		String breedsUrl = dogRestUrl+"/breeds/list/all";
		Duration timeoutDuration = Duration.ofMillis(dogBreedListTimeout);
		
		String messageBody = getRestAPIResources(breedsUrl, timeoutDuration);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> result = null;
		try {
			JsonNode breedsJson = objectMapper.readTree(messageBody).get("message");
			result = objectMapper.convertValue(breedsJson, new TypeReference<Map<String, Object>>(){});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			result = null;
		}
		return result;
	}

	@Override
	public List<Object> getBreed(String name) {
		String subbreedsUrl = dogRestUrl+"/breed/" + name + "/list";

		Duration timeoutDuration = Duration.ofMillis(dogBreedTimeout);
		String messageBody =  getRestAPIResources(subbreedsUrl, timeoutDuration);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Object> result = null;

		try {
			JsonNode breedJson = objectMapper.readTree(messageBody).get("message");
			result = objectMapper.convertValue(breedJson, new TypeReference<List<Object>>(){});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			result = null;
		}
		return result;
	}

	@Override
	public List<Object> getBreedImages(String breedName) {
		String imageUrl = dogRestUrl+"/breed/" + breedName + "/images";
		Duration timeoutDuration = Duration.ofMillis(100000);
		String messageBody = getRestAPIResources(imageUrl, timeoutDuration);
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Object> result = null;
		try {
			JsonNode imageJson = objectMapper.readTree(messageBody).get("message");
			result = objectMapper.convertValue(imageJson, new TypeReference<List<Object>>(){});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			result = null;
		}
		return result;
	}
	
	private String getRestAPIResources(String url, Duration timeoutDuration) {
		WebClient client = WebClient.create();

		ResponseSpec responseSpec = client.get()
		    .uri(url)
		    .retrieve();

		String responseBody =
				responseSpec.bodyToMono(String.class)
							.timeout(timeoutDuration)
							.onErrorResume(throwable -> timeoutFallBackMethod(throwable))
							.block();
		
		String messageBody;

		if (responseBody.isEmpty()) {
			return null;
		}
		
		else {
			messageBody =  responseBody;
		}
		return messageBody;
	}

	private Mono<? extends String> timeoutFallBackMethod(Throwable throwable) {
		return Mono.just("");
	}
}
