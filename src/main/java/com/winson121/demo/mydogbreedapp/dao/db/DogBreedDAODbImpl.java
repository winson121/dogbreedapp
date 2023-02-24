package com.winson121.demo.mydogbreedapp.dao.db;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TransactionRequiredException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.winson121.demo.mydogbreedapp.entity.Breed;
import com.winson121.demo.mydogbreedapp.exception.BreedNotFoundException;

@Repository
public class DogBreedDAODbImpl implements DogBreedDAODb{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Breed saveBreed(Breed breed) {
		// get current current session
		Session currentSession = entityManager.unwrap(Session.class);
	
		// save or update the dog Breed data
		currentSession.saveOrUpdate(breed);
		
		return breed;
	}

	@Override
	public void deleteBreed(int breedId) {
		// get the current current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// delete object with primary key
		Query<?> query = 
				currentSession.createQuery("delete from Breed where id=:breedId");
		
		query.setParameter("breedId", breedId);
		
		query.executeUpdate();

	}

	@Override
	public Collection<Breed> getBreedsFromDb() {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create query to get breeds
		Query<Breed> query = currentSession.createQuery("from Breed", Breed.class);
		
		// execute query and get results
		Collection<Breed> breeds = query.getResultList();

		// return results
		return breeds;
	}

	@Override
	public Breed getBreedFromDb(String breedName) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		Query<?> query =
				currentSession.createQuery("from Breed where breedName=:breedName");

		query.setParameter("breedName", breedName);

		Breed breed = null;

		try {
			breed = (Breed) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		// return results
		return breed;
	}

}
