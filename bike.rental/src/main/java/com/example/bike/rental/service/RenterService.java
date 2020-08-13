package com.example.bike.rental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bike.rental.domain.Renter;
import com.example.bike.rental.repository.RenterRepository;


@Service
public class RenterService {

	RenterRepository renterRepo;
	
	@Autowired
	public void setRenterRepo(RenterRepository renterRepo) {
		this.renterRepo = renterRepo;
	}
	
	public List<Renter> getRenters() {
		return renterRepo.findAll();
	}
	
	public Renter findByEmail(String email) {
		return renterRepo.findByEmail(email);
	}
	
	public Renter saveRenter(Renter renter) {
		return renterRepo.save(renter);
	}

}
