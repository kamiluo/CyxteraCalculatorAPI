package com.caforerof.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.caforerof.calculator.models.entity.Operand;
import com.caforerof.calculator.models.services.IOperandService;
import com.caforerof.calculator.responses.OperandResponse;

@RestController
@RequestMapping("/calculatorApi")
public class OperandController {
	@Autowired
	IOperandService operandService;
	
	@RequestMapping("/addOperand/{operand}/{sessionId}")
	@ResponseStatus(HttpStatus.OK)
	public OperandResponse addOperand(@PathVariable double operand, @PathVariable long sessionId) {
		Operand opd = operandService.createOperand(sessionId, operand);
		OperandResponse or = new OperandResponse();
		or.setOperand(opd.getValue());
		return or;
	}

}
