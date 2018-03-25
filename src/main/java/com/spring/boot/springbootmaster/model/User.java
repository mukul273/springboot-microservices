package com.spring.boot.springbootmaster.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="User Bean")
@JsonIgnoreProperties(value= {"id"}) //THis is another way filtering the fields to be in output, also called as static filtering
@JsonFilter("someFilter") //This is for Dynamic filter
public class User {
	
	public User() {
		
	}
	public User(int i, String name, Date bdate, String address) {
		this.id = i;
		this.name = name;
		this.birthDate = bdate;
		this.address = address;
	}
	
	@lombok.NonNull
	@JsonIgnore // one way to filter the field being passed into output
	private Integer id;
	
	@Size(min=2, message="Name should be atleast 2 characters")
	private String name;
	
	@Past(message="Birthdate can't be in the past")
	@ApiModelProperty(notes="Birthdate should be in the past")
	private Date birthDate;
	
	
	private String address;
}