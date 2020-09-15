package com.example.bike.rental.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.bike.rental.domain.Bike;

@Repository
public interface BikeRepository extends CrudRepository<Bike, Long>{

	List<Bike> findAll();
	
	@Query("Select b " + "From Bike b " + "Where b.enabled = true " )
    List<Bike> findEnabledBikes();
    
    @Query("Select b " + "From Bike b " + "Where b.enabled = false " )
    List<Bike> findDisabledBikes();
    
    @Query("Select b " + "From Bike b " + "Where b.enabled = true and b.id " + "Not In " + 
    		" (Select r.bike " + "From Rental r " + 
    		"where ( r.beginDate " +  "Between ?1 and ?2 )" + 
    		" or " + "r.endDate " +  "Between ?1 and ?2 )" )
    List<Bike> findRentableBikes( LocalDate startDate, LocalDate endDate);
    
    @Query("Select b " + "From Bike b " + "Where b.id = ?1 " )
    Bike findBikeById(Long id);
    
    @Query("Select b " + "From Bike b " + "Where b.frameNumber = ?1 " )
    Bike findByFrameNumber(String frameNumber);
    
}
