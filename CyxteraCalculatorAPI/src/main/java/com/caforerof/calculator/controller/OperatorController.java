package com.caforerof.calculator.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caforerof.calculator.responses.OperatorResponse;

@RestController
@RequestMapping("/calculatorApi")
public class OperatorController {

	
	@RequestMapping("/setOperator/{operator}")
	public OperatorResponse setOperator(@PathVariable String operator) {
		OperatorResponse or = new OperatorResponse();
		or.setOperator(operator);
		return or;
	}
}
