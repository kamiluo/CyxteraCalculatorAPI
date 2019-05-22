package com.caforerof.calculator.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.caforerof.calculator.responses.Response;

@RestControllerAdvice
public class CustomErrHandling {
  
  @ExceptionHandler(Exception.class)
  public Response handleSystemExceptions(Exception ex) {
	Response resp = new Response();
	resp.setMessage(ex.getMessage());
	resp.setResponseCode(5);
    return resp;
  }
}