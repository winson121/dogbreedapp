package com.winson121.springboot.demo.dogbreedapp.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winson121.springboot.demo.dogbreedapp.dto.BreedDTO;
import com.winson121.springboot.demo.dogbreedapp.dto.SubbreedDTO;
import com.winson121.springboot.demo.dogbreedapp.entity.Breed;
import com.winson121.springboot.demo.dogbreedapp.service.DogBreedService;

@RestController
@RequestMapping("/api")
public class DogBreedRestController {
	
	@Autowired
	private DogBreedService dogBreedService;
	
	@GetMapping("/breeds")
	public ResponseEntity<Set<BreedDTO>> getBreeds() {
		
		Set<BreedDTO> breeds = dogBreedService.getBreeds();
		
		
		if (breeds != null) {
			return new ResponseEntity<>(breeds, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/breed/{breedName}/list")
	public ResponseEntity<List<SubbreedDTO>> getSubbreeds(@PathVariable String breedName) {
		List<SubbreedDTO> subbreeds = dogBreedService.getSubbreeds(breedName);
		
		
		if (subbreeds != null) {
			return new ResponseEntity<>(subbreeds, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/breeds")
	public ResponseEntity<Breed> saveBreed(@RequestBody Breed breed) {
		// just in case an id is pass in the JSON, we will set the id to 0
		// this will force a save of new item instead of updating the course
		if (breed == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		breed.setId(0);
		
		// assigned breed
		Breed savedBreed = dogBreedService.saveBreed(breed);
		
		return new ResponseEntity<>(savedBreed, HttpStatus.OK);
	}
	
}
