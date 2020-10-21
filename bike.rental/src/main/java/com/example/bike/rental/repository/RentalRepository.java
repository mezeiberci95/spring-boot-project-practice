package com.example.bike.rental.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.bike.rental.domain.Rental;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long>{

	List<Rental> findAll();
}
