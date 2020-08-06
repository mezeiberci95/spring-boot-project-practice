package com.example.bike.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.bike.rental.domain.Bike;

@Repository
public interface BikeRepository extends CrudRepository<Bike, Long>{

	List<Bike> findAll();
	
	@Query("Select b " + "From Bike b " + "Where b.enabled = true " )
    List<Bike> getEnabledCars();
    
    @Query("Select b " + "From Bike b " + "Where b.enabled = false " )
    List<Bike> getDisabledCars();
}
