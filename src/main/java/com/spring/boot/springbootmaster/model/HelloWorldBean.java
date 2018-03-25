package com.spring.boot.springbootmaster.model;

import lombok.Data;

@Data
public class HelloWorldBean {
	
	private String msg;

	public HelloWorldBean(String msg) {
		this.msg = msg;
	}
}