package com.winson121.demo.mydogbreedapp.dao.db;

import java.util.Collection;

import com.winson121.demo.mydogbreedapp.entity.Breed;

public interface DogBreedDAODb {

	Breed saveBreed(Breed breed);

	void deleteBreed(int breedId);

	Collection<Breed> getBreedsFromDb();

	Breed getBreedFromDb(String breedName);
}
