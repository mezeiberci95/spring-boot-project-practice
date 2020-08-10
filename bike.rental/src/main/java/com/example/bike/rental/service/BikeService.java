package com.example.bike.rental.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bike.rental.domain.Bike;
import com.example.bike.rental.repository.BikeRepository;

@Service
public class BikeService {
	
	BikeRepository bikeRepo;
	
	/*EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
    EntityManager entitymanager = emfactory.createEntityManager();*/
    
	public BikeRepository getBikeRepo() {
		return bikeRepo;
	}
	
	
	@Autowired
	public void setBikeRepo(BikeRepository bikeRepo) {
		this.bikeRepo = bikeRepo;
	}
    
	public List<Bike> getBikes() {
		return bikeRepo.findAll();
	}
	
	public void saveBike(Bike bike) {
		bikeRepo.save(bike);
	}    

}
