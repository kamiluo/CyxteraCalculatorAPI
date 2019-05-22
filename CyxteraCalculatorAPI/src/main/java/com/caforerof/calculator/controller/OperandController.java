package com.caforerof.calculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.caforerof.calculator.responses.OperandResponse;

@RestController
@RequestMapping("/calculatorApi")
public class OperandController {
	
	@RequestMapping("/addOperand/{operand}")
	@ResponseStatus(HttpStatus.OK)
	public OperandResponse addOperand(@PathVariable double operand) {
		OperandResponse or = new OperandResponse();
		or.setOperand(operand);
		return or;
	}

}
