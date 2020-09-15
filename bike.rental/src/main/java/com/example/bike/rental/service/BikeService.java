package com.example.bike.rental.service;

import java.time.LocalDate;
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
    
	public BikeRepository findBikeRepo() {
		return bikeRepo;
	}
	
	@Autowired
	public void setBikeRepo(BikeRepository bikeRepo) {
		this.bikeRepo = bikeRepo;
	}
    
	public List<Bike> findBikes() {
		return bikeRepo.findAll();
	}
	
	public void saveBike(Bike bike) {
		bikeRepo.save(bike);
	}

	public List<Bike> findEnabledBikes() {
		return bikeRepo.findEnabledBikes();
	}
	
	public List<Bike> findDisabledBikes() {
		return bikeRepo.findDisabledBikes();
	}
	
	public List<Bike> findRentableBikes(LocalDate startDate, LocalDate endDate) {
		return bikeRepo.findRentableBikes(startDate, endDate);
	}
	
	public Bike findBikeById(Long id) {
		return bikeRepo.findBikeById(id);
	}
	
	public Bike findBikeByFrameNumber(String frameNumber) {
		return bikeRepo.findByFrameNumber(frameNumber);
	}
}
