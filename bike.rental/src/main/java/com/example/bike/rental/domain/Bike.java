package com.example.bike.rental.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BIKE")
@NoArgsConstructor
public @Data class Bike {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE )
	private Long id; //primary key
	
	@NotNull
	@Column(name = "manufacture_date")
	private int manufactureDate;
	
	
	@NotNull
	@Column(name = "bike_type")
	private String bikeType;
	
	@NotNull
	@Column(name = "daily_price")
	private int dailyPrice;
	
	@NotNull
	@Column(name = "enabled")
	private boolean enabled;
	
	@OneToMany(mappedBy="bike")
	private List<Rental> rentals;

	public Bike(@NotNull int manufactureDate, @NotNull String bikeType, @NotNull int dailyPrice,
			@NotNull boolean enabled) {
		super();
		this.manufactureDate = manufactureDate;
		this.bikeType = bikeType;
		this.dailyPrice = dailyPrice;
		this.enabled = enabled;
	}
	
	
}
