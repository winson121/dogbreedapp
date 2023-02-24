package com.winson121.demo.mydogbreedapp.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.winson121.demo.mydogbreedapp.dto.BreedDTO;
import com.winson121.demo.mydogbreedapp.dto.ImageDTO;
import com.winson121.demo.mydogbreedapp.dto.SubbreedDTO;
import com.winson121.demo.mydogbreedapp.entity.Breed;
import com.winson121.demo.mydogbreedapp.entity.Image;
import com.winson121.demo.mydogbreedapp.entity.Subbreed;

@Component
public class Mapper {
	
	public BreedDTO toDto(Breed breed) {
		String name = breed.getBreedName();
		List<SubbreedDTO> subbreedsDto = (breed.getSubbreeds() != null) ?
										  breed.getSubbreeds()
										  	   .stream()
										  	   .map(sb -> toDto(sb))
										  	   .collect(Collectors.toList()) : null;
		
		return new BreedDTO(breed.getId(), name, subbreedsDto);
	}
	
	public SubbreedDTO toDto(Subbreed subbreed) {
		String name = subbreed.getSubbreedName();
		List<ImageDTO> images = (subbreed.getImages() != null) ?
								 subbreed.getImages()
								 		 .stream()
								 		 .map(im -> toDto(im))
								 		 .collect(Collectors.toList()) : null;
		return new SubbreedDTO(subbreed.getId(), name, images);
	}
	
	public ImageDTO toDto(Image image) {
		String name = image.getImageLink();
		
		return new ImageDTO(image.getId(), name);
	}
	
	public Breed toEntity(BreedDTO breedDto) {
		String name = breedDto.getName();
		List<Subbreed> subbreeds = (breedDto.getSubbreeds() != null) ?
									breedDto.getSubbreeds()
											.stream()
											.map(sb -> toEntity(sb))
											.collect(Collectors.toList()): null;
		return new Breed(breedDto.getId(), name, subbreeds);
	}
	
	public Subbreed toEntity(SubbreedDTO subbreedDto) {
		String name = subbreedDto.getName();
		List<Image> images = (subbreedDto.getImages() != null) ?
				 			  subbreedDto.getImages()
				 			  			 .stream()
				 			  			 .map(im -> toEntity(im))
				 			  			 .collect(Collectors.toList()): null;
		return new Subbreed(subbreedDto.getId(), name, images);
	}
	
	public Image toEntity(ImageDTO imageDto) {
		String name = imageDto.getName();
		
		return new Image(imageDto.getId(), name);
	}
}
