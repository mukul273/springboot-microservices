package com.spring.boot.springbootmaster.controller;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.springbootmaster.model.HelloWorldBean;

@RestController
@RequestMapping("/helloworld")
public class HelloWorld {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public String helloworld() {
		return "Chimp says Hello world to Spring Boot base URL";
	}
	
	@GetMapping("/bean")
	public HelloWorldBean helloworldBean() {
		return new HelloWorldBean("Chimps bean return");
	}
	
	@GetMapping("/pathvariable/{name}")
	public HelloWorldBean helloworldPathVariable(@PathVariable String name) {
		return new HelloWorldBean("Chimps path variable returns "+name);
	}
	
	@GetMapping(path = "/int")
	public String helloworldInt(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
}
