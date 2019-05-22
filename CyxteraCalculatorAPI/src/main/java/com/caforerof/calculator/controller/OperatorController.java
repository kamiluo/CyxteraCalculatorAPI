package com.caforerof.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caforerof.calculator.models.entity.Operand;
import com.caforerof.calculator.models.entity.Operator;
import com.caforerof.calculator.models.services.IOperatorService;
import com.caforerof.calculator.responses.CustomErrorCalcException;
import com.caforerof.calculator.responses.OperatorResponse;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/calculatorApi")
public class OperatorController {

	@Autowired
	IOperatorService operatorService;
	
	@RequestMapping("/setOperator/{operator}/{sessionId}")
	public OperatorResponse setOperator(@PathVariable String operator, @PathVariable long sessionId) {
		Operator opr = null;
		Operand resp = null;

		OperatorResponse or = new OperatorResponse();
		try {
			opr = operatorService.createOperator(sessionId, operator);			
		} catch (Exception e) {
			or.setMessage("Error al crear la operación.");
			or.setResponseCode(1);
			or.setResult(null);
			or.setOperator(null);
			return or;
		}
		
		try {
			resp = operatorService.execResult(opr, sessionId);
			or.setMessage("Operación ejecutada correctamente.");
			or.setResponseCode(200);
			or.setResult(resp.getValue());
			or.setOperator(opr.getValue());
		} catch (CustomErrorCalcException e) {
			or.setMessage(e.getMessage());
			or.setResponseCode(e.getErrNum());
			or.setResult(null);
			or.setOperator(null);
		} catch (Exception e) {
			or.setMessage("Error al ejecutar la operación.");
			or.setResponseCode(3);
			or.setResult(null);
			or.setOperator(null);
		}
		return or;
	}
}
