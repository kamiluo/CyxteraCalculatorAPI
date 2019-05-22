package com.caforerof.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caforerof.calculator.models.entity.Operator;
import com.caforerof.calculator.models.services.IOperatorService;
import com.caforerof.calculator.responses.OperatorResponse;

@RestController
@RequestMapping("/calculatorApi")
public class OperatorController {

	@Autowired
	IOperatorService operatorService;
	
	@RequestMapping("/setOperator/{operator}/{sessionId}")
	public OperatorResponse setOperator(@PathVariable String operator, @PathVariable long sessionId) {
		Operator opr = operatorService.createOperator(sessionId, operator);
		operatorService.execResult(opr, sessionId);
		OperatorResponse or = new OperatorResponse();
		or.setOperator(opr.getValue());
		return or;
	}
}
