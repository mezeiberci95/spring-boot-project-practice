package com.example.bike.rental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bike.rental.domain.Rental;
import com.example.bike.rental.repository.RentalRepository;

@Service
public class RentalService {
	RentalRepository rentalRepo;
	
	@Autowired
	public void setRenterRepo(RentalRepository rentalRepo) {
		this.rentalRepo = rentalRepo;
	}
	
	public List<Rental> getRentals() {
		return rentalRepo.findAll();
	}
	
	public Rental saveRental(Rental rental) {
		return rentalRepo.save(rental);
	}
}
