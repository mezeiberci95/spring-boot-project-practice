package com.example.bike.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.bike.rental.domain.Renter;

@Repository
public interface RenterRepository extends CrudRepository<Renter, Long> {

	List<Renter> findAll();
	
	@Query("select r from Renter r where r.email = ?1")
	Renter findByEmail(String email);
}
