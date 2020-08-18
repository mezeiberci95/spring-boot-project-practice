package com.example.bike.rental.domain;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME) 
@Constraint(validatedBy=MyDateValidator.class) 
public @interface ValidDates {
    String message() default "Start date must be before end date";
    /*Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};*/
    String startDate();
    String endDate();
    
    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
    	ValidDates[] value();
    }
}
