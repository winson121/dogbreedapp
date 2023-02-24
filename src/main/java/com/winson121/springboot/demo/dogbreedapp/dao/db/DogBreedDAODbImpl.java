package com.winson121.springboot.demo.dogbreedapp.dao.db;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.winson121.springboot.demo.dogbreedapp.entity.Breed;

@Repository
public class DogBreedDAODbImpl implements DogBreedDAODb{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Breed saveBreed(Breed breed) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
	
		// save or update the dog Breed data
		currentSession.saveOrUpdate(breed);
		
		return breed;
	}

}
