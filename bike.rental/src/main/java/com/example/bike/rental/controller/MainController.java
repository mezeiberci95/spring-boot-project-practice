package com.example.bike.rental.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bike.rental.config.MvcConfig;
import com.example.bike.rental.domain.RentDates;
import com.example.bike.rental.service.BikeService;
import com.example.bike.rental.service.RentalService;
import com.example.bike.rental.service.RenterService;

@Controller
public class MainController {
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	private static final DateFormat urlFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private BikeService bikeService;
	@Autowired
	private RenterService renterService;
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private MvcConfig mvcConfig ;
	
	@Autowired
	public void setCarService(BikeService bikeService) {
		this.bikeService = bikeService;
	}

	@Autowired
	public void setRenterService(RenterService renterService) {
		this.renterService = renterService;
	}
	
	@Autowired
	public void setRentalService(RentalService rentalService) {
		this.rentalService = rentalService;
	}
	@Autowired
	public MvcConfig getMvcConfig() {
		return mvcConfig;
	}
	@Autowired
	public void setMvcConfig(MvcConfig mvcConfig) {
		this.mvcConfig = mvcConfig;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("pageTitle", "Bike rental");
		model.addAttribute("currentDate", LocalDate.now());
		model.addAttribute("rentDates", new RentDates());
		return "index";
	}
	

}
