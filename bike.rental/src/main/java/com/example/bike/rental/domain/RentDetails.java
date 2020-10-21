package com.example.bike.rental.domain;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public @Data class RentDetails {
	@NotNull(message = "Name is too short")
	@NotBlank(message = "Name cannot be empty")
	@Size(min=5, max=30, message="Name too short")
	@Pattern(regexp="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message="Not a valid name")
	private String name;
	
	@NotNull(message = "Email cannot be null.")
	@NotBlank(message = "Email cannot be empty") 
	@Pattern(
		    regexp = "^[A-Za-z0-9+_.-]+@(.+)$", 
		    message = "Not a valid email format."
		    )
	private String email;
	
	@NotNull(message = "Address cannot be null.")
	@Pattern(regexp = "^[a-zA-Z]+(([a-zA-Z ])?[a-zA-Z]*)*\\s+[0-9]+$", message = "Address doesn't seem valid" )
	@NotBlank(message = "Address cannot be empty")
	private String address;
	
	@NotNull(message = "Phone cannot be null.")
	@NotBlank(message = "Phone cannot be empty")
	@Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Phone number format should be like +20 3248371")
	private String phone;
	
	@Min(1)
	private int days;
	@Min(1000)
	private int price;
	private String startDate;
	private String endDate;
	
	@NotNull(message = "Selected bike's id cannot be empty") 
	private long selectedBikeId;

	public RentDetails(String startDate, String endDate, long selectedBikeId) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.selectedBikeId = selectedBikeId;
	}
	
}

