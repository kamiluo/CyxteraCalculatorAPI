package com.caforerof.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.caforerof.calculator.models.entity.Operand;
import com.caforerof.calculator.models.services.IOperandService;
import com.caforerof.calculator.responses.CustomErrorCalcException;
import com.caforerof.calculator.responses.OperandResponse;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/calculatorApi")
public class OperandController {
	@Autowired
	IOperandService operandService;
	
	@RequestMapping("/addOperand/{operand}/{sessionId}")
	@ResponseStatus(HttpStatus.OK)
	public OperandResponse addOperand(@PathVariable double operand, @PathVariable long sessionId) {
		OperandResponse or = new OperandResponse();
		try {
			Operand opd = operandService.createOperand(sessionId, operand);
			or.setMessage("Operando creado correctamente.");
			or.setResponseCode(0);
			or.setOperand(opd.getValue());
			return or;
		} catch (CustomErrorCalcException e) {
			or.setMessage(e.getMessage());
			or.setResponseCode(e.getErrNum());
			return or;
		} catch (Exception e) {
			or.setMessage("Error creando el operando.");
			or.setResponseCode(500);
			return or;
		}
	}

}
