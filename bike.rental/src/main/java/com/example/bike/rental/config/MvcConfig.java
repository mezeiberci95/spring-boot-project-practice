package com.example.bike.rental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

    	registry
        .addResourceHandler("/css/**")
        .addResourceLocations("classpath:/static/css/");
    	
    	registry
        .addResourceHandler("/img/**")
        .addResourceLocations("classpath:/static/");
    	
    	registry
        .addResourceHandler("/**")
        .addResourceLocations("classpath:/templates/");
    	
    }
	
	@Override
	public void configureDefaultServletHandling(
      DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
