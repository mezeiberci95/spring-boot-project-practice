package com.example.bike.rental.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class RentDates {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent
	@NotNull(message = "Date cannot be null")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent(message = "Date must be future or present")
	@NotNull(message = "Date cannot be null")
	private LocalDate endDate;
	
	public RentDates(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Boolean validate() {
	      return (this.startDate.isBefore(this.endDate) || this.startDate.equals(this.endDate));
	  }
}
