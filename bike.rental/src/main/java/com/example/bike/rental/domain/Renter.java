package com.example.bike.rental.domain;

import java.util.ArrayList;
import java.util.Date;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(name="RENTER")
@NoArgsConstructor
@EqualsAndHashCode
public @Data class Renter {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Column(name = "email", unique=true)
	private String email;
	@NotNull
	@Column(name = "address")
	private String address;
	@NotNull
	@Column(name = "phone_number", unique=true)
	private String phoneNumber;
	@OneToMany(mappedBy = "renter")
	private List<Rental> rentals;
	
	public Renter(String name, String email, String address, String phoneNumber) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
}
