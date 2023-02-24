package com.winson121.springboot.demo.dogbreedapp.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.winson121.springboot.demo.dogbreedapp.dto.BreedDTO;
import com.winson121.springboot.demo.dogbreedapp.dto.ImageDTO;
import com.winson121.springboot.demo.dogbreedapp.dto.SubbreedDTO;
import com.winson121.springboot.demo.dogbreedapp.entity.Breed;
import com.winson121.springboot.demo.dogbreedapp.entity.Subbreed;

@Component
public class Mapper {
	
	public BreedDTO toDto(Breed breed) {
		String name = breed.getBreedName();
		List<SubbreedDTO> subbreeds = 
				breed.getSubbreeds()
					 .stream()
					 .map(sb -> new SubbreedDTO(sb.getSubbreedName()))
					 .collect(Collectors.toList());
		
		return new BreedDTO(name, subbreeds);
	}
	
	public SubbreedDTO toDto(Subbreed subbreed) {
		String name = subbreed.getSubbreedName();
		List<ImageDTO> images = 
			 subbreed.getImages()
					 .stream()
					 .map(im -> new ImageDTO(im.getImageLink()))
					 .collect(Collectors.toList());
		return new SubbreedDTO(name, images);
	}
	
}
