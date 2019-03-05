package com.cssl.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {
	
	@ExceptionHandler(Exception.class)
	public String defult(Exception e) {
		System.out.println("错误"+e);
		return "error";
	}
}
