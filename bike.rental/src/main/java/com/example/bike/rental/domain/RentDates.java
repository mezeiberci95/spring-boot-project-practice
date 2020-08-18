package com.example.bike.rental.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@ValidDates.List({ 
	@ValidDates(
    	      startDate = "2020-12-31", 
    	      endDate = "2020-12-21", 
    	      message = "End date must be after start date."
    	    ), 

    	})
public @Data class RentDates {

	///@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@FutureOrPresent
	//@NotNull(message = "Date cannot be null")
	private String startDate;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@FutureOrPresent(message = "Date must be future or present")
	//@NotNull(message = "Date cannot be null")
	private String endDate;
	
	public RentDates(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
}
