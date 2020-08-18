package com.example.bike.rental.domain;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class MyDateValidator implements ConstraintValidator< ValidDates, 
Object> {
	
	private String start;
	private String end;

	//@Override
	public void initialize(ValidDates constraintAnnotation) {
		this.start = constraintAnnotation.startDate();
		this.end = constraintAnnotation.endDate();
	}
	
	//@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) 
	{
		Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(start);
		Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(end);

	    return fieldValue.equals(fieldMatchValue);
		//return (value != null && LocalDate.parse(start).isBefore(LocalDate.parse(end)));
	}

}