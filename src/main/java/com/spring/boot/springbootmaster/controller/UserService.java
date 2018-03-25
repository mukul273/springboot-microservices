package com.spring.boot.springbootmaster.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.spring.boot.springbootmaster.dao.UserDao;
import com.spring.boot.springbootmaster.exception.UserNotFoundException;
import com.spring.boot.springbootmaster.model.User;

@RestController
@RequestMapping("/users")
public class UserService {

	@Autowired
	private UserDao service;
	
	@GetMapping("/findall")
	public MappingJacksonValue getAllUsers() {
		List<User> findAllUsers = service.findAllUsers();
		
		// Dynamic filtering
		MappingJacksonValue value = new MappingJacksonValue(findAllUsers);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("address");
		FilterProvider filters = new SimpleFilterProvider().addFilter("someFilter", filter);
		value.setFilters(filters);
		
		return value;
	}
	
	@GetMapping("/user/{id}")
	public User findUser(@PathVariable Integer id) {
		return service.getUser(id);
	}
	
	@GetMapping("/userwithexception/{id}")
	public Resource<User> findUserwithException(@PathVariable Integer id)  {
		User user = service.getUser(id);
		
		if(user==null)
			throw new UserNotFoundException("id - "+id);
		
		//HATEOAS - HyperMedia As The Engine Of Application State
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	@PostMapping("/makeuser")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		service.saveUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}