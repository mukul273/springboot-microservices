package com.spring.boot.springbootmaster.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ExceptionResponse {
	
	private Date timestamp;
	private String msg;
	private String details;
	
	public ExceptionResponse(Date timestamp, String msg, String details) {
		super();
		this.timestamp = timestamp;
		this.msg = msg;
		this.details = details;
	}
}