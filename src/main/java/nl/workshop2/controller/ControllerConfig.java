package nl.workshop2.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ControllerConfig implements WebMvcConfigurer {

	//Hier de 'lege' controllers plaatsen (met alleen een GetMapping (en ook PostMapping?) die een simpele String returnen
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
//		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/main").setViewName("main");
	}
}
