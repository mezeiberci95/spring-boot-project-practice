package com.example.bike.rental.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bike.rental.config.MvcConfig;
import com.example.bike.rental.domain.Bike;
import com.example.bike.rental.domain.RentDates;
import com.example.bike.rental.domain.RentDetails;
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
	public void setBikeService(BikeService bikeService) {
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
		return "date_picker_form";
	}
	
	@PostMapping("/bikes")
	public String listBikes(@Valid RentDates rentDates, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors() || rentDates == null || rentDates.getStartDate() == null || rentDates.getEndDate() == null || !rentDates.validate()) {
			return "redirect:invalid-date-selected";
		}
		model.addAttribute("pageTitle", "Bike rental");
		model.addAttribute("startDate", rentDates.getStartDate().toString());
		model.addAttribute("endDate", rentDates.getEndDate().toString());
		model.addAttribute("bikes", bikeService.getRentableBikes(rentDates.getStartDate(), rentDates.getEndDate()));
		
		return "bikes_listed";
	}
	
	@GetMapping("/invalid-date-selected")
	public String invalidDateErrorPage(Model model) {
		model.addAttribute("error", "Invalid date error");
		model.addAttribute("message", "Invalid date selected. Start date must be before end date.");
		return "invalid_date_page";
	}

	
	@GetMapping("/bikes/{id}/{startDateFromURL}/{endDateFromURL}")
	public String showRentForm(@PathVariable(required = true) Long id, @PathVariable(required = true) String startDateFromURL, @PathVariable(required = true) String endDateFromURL, Model model) throws ParseException {
		model.addAttribute("pageTitle", "Bike rental");
		//model.addAttribute("startDate", startDate);
		//model.addAttribute("endDate", endDate);
		
		Bike selectedBike = bikeService.getBikeById(id);
		model.addAttribute("bike", selectedBike);
		
		LocalDate start = LocalDate.parse(startDateFromURL);
		LocalDate end = LocalDate.parse(endDateFromURL);
		
		if(!(start.isBefore(end) || start.equals(end)) || start.isBefore(LocalDate.now())) {
			model.addAttribute("error", "Invalid date error");
			model.addAttribute("message", "Invalid or past date given as argument");
			return "redirect:invalid_date_page";
		}
		
		RentDetails rd = new RentDetails(startDateFromURL, endDateFromURL, selectedBike.getId());//urlFormat.format(start), urlFormat.format(end), selectedBike.getId());
		rd.setPrice(rd.getDays() * selectedBike.getDailyPrice());
		model.addAttribute("frameNumber",selectedBike.getFrameNumber());
		model.addAttribute("bikeType",selectedBike.getBikeType());
		model.addAttribute("rentDetailsForm", rd);
		
		return "rent_form_page";
	}
	 
	@PostMapping("/bikes/{id}/{startDateFromURL}/{endDateFromURL}")
	public String validateFormAndSaveRental(@Valid @ModelAttribute("rentDetailsForm") RentDetails rentDetails, BindingResult bindingResult) {
		try {
			if(bindingResult.hasErrors()){
				return "rent_form_page";
			}
			System.out.println(rentDetails.getAddress());
		} catch (Exception e) {
			System.out.println(e.toString());
			//SHOW ERROR MESSAGE HERE
			return "error"; 
		}
		
		return "redirect:/confirmed";
	}
	
	@GetMapping("/confirmed")
	public String showConfirmation(){
		return "confirmed";
	}
	
	@GetMapping("/allbikes")
	public String showAllBikes(Model model){
		model.addAttribute("bikes", bikeService.getBikes());
		model.addAttribute("pageTitle", "Bike rental");
		return "all_bikes_page";
	}
	
	
	
}
