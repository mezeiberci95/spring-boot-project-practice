package com.example.bike.rental.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		Bike bike1 = new Bike(1995, "asd123", "Puch Mistral", 5000, true);
		bike1.setId(1L);
		Bike bike2 = new Bike(1990, "asd122", "Puch Clubman", 4000, true);
		bike2.setId(2L);
		
		List<Bike> bikeList = new ArrayList<Bike>();
		bikeList.add(bike1);
		bikeList.add(bike2);
		
		model.addAttribute("bikes",bikeList); // bikeService.getBikes());
		return "bikes_listed";
	}
	
	@GetMapping("/invalid-date-selected")
	public String invalidDateErrorPage(Model model) {
		model.addAttribute("error", "Invalid date error");
		model.addAttribute("message", "Invalid date selected. Start date must be before end date.");
		return "invalid_date_page";
	}

	
	@GetMapping("/cars/{id}/{startDate}/{endDate}")
	public String showRentForm(@PathVariable(required = true) long id, @PathVariable(required = true) String startDate, @PathVariable(required = true) String endDate, Model model) throws ParseException {
		model.addAttribute("pageTitle", "Car rental");
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		//model.addAttribute("days", 1);
		
		Bike selectedBike = bikeService.getBikeById(id);
		model.addAttribute("bike", selectedBike);
		
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		
		if(!(start.isBefore(end) || start.equals(end)) || start.isBefore(LocalDate.now())) {
			model.addAttribute("error", "Invalid date error");
			model.addAttribute("message", "Invalid or past date given as argument");
			return "redirect:invalid_date_page";
		}
		
		int days = countDiffBetweenDates(start, end);
		int sumPrice = days * selectedBike.getDailyPrice();
		
		RentDetails rd = new RentDetails(days, sumPrice, urlFormat.format(start), urlFormat.format(end), selectedBike.getId());
		model.addAttribute("rentDetails", rd);
		model.addAttribute("frameNumber",selectedBike.getFrameNumber());
		
		return "rentForm";
	}
	
	public int countDiffBetweenDates(LocalDate startDate, LocalDate endDate) {
	    Period period = Period.between(startDate, endDate);
	    int diff = period.getDays() + 1;
	 
	    return diff;
	}
}
