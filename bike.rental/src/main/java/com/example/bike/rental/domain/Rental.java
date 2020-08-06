package com.example.bike.rental.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="RENTAL")
@NoArgsConstructor
public @Data class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE )
	private Long id;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "begin_date")
	private LocalDate beginDate;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@NotNull
	@Column(name = "days_of_rental")
	private int daysOfRental;
	
	@NotNull
	@Column(name = "sum_price")
	private int sumPrice;
	@ManyToOne
	private Renter renter;
	
	@ManyToOne
	private Bike bike;

	public Rental(LocalDate beginDate, LocalDate endDate, int daysOfRental, int sumPrice, Renter renter, Bike bike) {
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.daysOfRental = daysOfRental;
		this.sumPrice = sumPrice;
		this.renter = renter;
		this.bike = bike;
	}
	
}
