package com.winson121.demo.mydogbreedapp.controller;
import java.util.*;

import com.winson121.demo.mydogbreedapp.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.winson121.demo.mydogbreedapp.dto.BreedDTO;
import com.winson121.demo.mydogbreedapp.dto.ImageDTO;
import com.winson121.demo.mydogbreedapp.entity.Breed;
import com.winson121.demo.mydogbreedapp.exception.BreedErrorResponse;
import com.winson121.demo.mydogbreedapp.exception.BreedNotFoundException;
import com.winson121.demo.mydogbreedapp.service.DogBreedService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class DogBreedRestController {
	
	@Autowired
	private DogBreedService dogBreedService;

	// add an initbinder ... to convert trim input strings
	// remove leading and trailing whitespace
	// resolve issue for our validation
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/breeds")
	public ResponseEntity<Set<BreedDTO>> getBreeds() {
		
		Set<BreedDTO> breeds = dogBreedService.getBreeds();
		
		
		if (breeds != null) {
			return new ResponseEntity<>(breeds, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/breed/{breedName}")
	public ResponseEntity<BreedDTO> getBreed(@PathVariable String breedName) {
		BreedDTO breed = dogBreedService.getBreed(breedName);
		
		if (breed != null) {
			return new ResponseEntity<>(breed, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/breed/{breedName}/images")
	public ResponseEntity<Map<String, List<ImageDTO>>> getBreedImages(@PathVariable String breedName) {
		List<ImageDTO> images = dogBreedService.getBreedImages(breedName);
		System.out.println(images);
		Map<String, List<ImageDTO>> breedImages = new HashMap<>();
		breedImages.put(breedName, images);
		if (images != null) {
			return new ResponseEntity<>(breedImages, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/breeds/v2")
	public ResponseEntity<Collection<Breed>> getBreedsV2() {
		Collection<Breed> breeds = dogBreedService.getBreedsFromDb();
		
		return new ResponseEntity<>(breeds, HttpStatus.OK);
	}
	
	@PostMapping("/breeds/v2")
	public ResponseEntity<Breed> saveBreed(@Valid @RequestBody BreedDTO breedDto) {
		// just in case an id is pass in the JSON, we will set the id to 0
		// this will force a save of new item instead of updating the course
		if (breedDto == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		breedDto.setId(0);
		
		// assigned breed
		Breed savedBreed = dogBreedService.saveBreed(breedDto);
		
		return new ResponseEntity<>(savedBreed, HttpStatus.OK);
	}
	
	@PutMapping("/breeds/v2")
	public ResponseEntity<Breed> updateBreed(@Valid @RequestBody BreedDTO breedDto) {
		if(breedDto == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		System.out.println("breedDTO id: " + breedDto.getId());
		// assigned breed
		Breed updatedBreed = dogBreedService.updateBreed(breedDto);
		
		return new ResponseEntity<>(updatedBreed, HttpStatus.OK);
	}
	
	@DeleteMapping("/breeds/{breedId}/v2")
	public ResponseEntity<String> deleteBreed(@PathVariable int breedId) {
		
		dogBreedService.deleteBreed(breedId);
		
		return new ResponseEntity<>("Deleted breed id - " + breedId, HttpStatus.OK);
	}
	@ExceptionHandler
    public ResponseEntity<BreedErrorResponse> handleException(BreedNotFoundException exc) {

        BreedErrorResponse error = new BreedErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BreedErrorResponse> handleException(Exception exc) {

        BreedErrorResponse error = new BreedErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
