package com.caforerof.calculator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caforerof.calculator.models.entity.CyxteraLog;
import com.caforerof.calculator.models.entity.Operand;
import com.caforerof.calculator.models.entity.Operator;
import com.caforerof.calculator.models.entity.SessionId;
import com.caforerof.calculator.models.services.ICyxteraLogService;
import com.caforerof.calculator.models.services.IOperandService;
import com.caforerof.calculator.models.services.IOperatorService;
import com.caforerof.calculator.models.services.ISessionIdService;;

@RestController
@RequestMapping("/consultar")
public class QueriesController {

	@Autowired
	ICyxteraLogService cyxteraLogService;

	@Autowired
	IOperandService operandService;

	@Autowired
	IOperatorService operatorService;

	@Autowired
	ISessionIdService sessionIdService;
	
	@RequestMapping("/logs")
	public List<CyxteraLog> getLogs() {
		return cyxteraLogService.findAll();
	}
	
	@RequestMapping("/operandos")
	public List<Operand> getOperands() {
		List<Operand> result = operandService.findAll();
		result.forEach(it -> {it.getSessionId().setOperands(null);
								it.getSessionId().setOperators(null);});
		return result;
	}
	
	@RequestMapping("/operadores")
	public List<Operator> getOperators() {
		List<Operator> result = operatorService.findAll();
		result.forEach(it -> {it.getSessionId().setOperands(null);
								it.getSessionId().setOperators(null);});
		return result;
	}
	
	@RequestMapping("/sesiones")
	public List<SessionId> getSessions() {
		List<SessionId> result = sessionIdService.findAll();
		result.forEach(it -> {it.setOperands(null); it.setOperators(null);});
		return result;
	}
	
}