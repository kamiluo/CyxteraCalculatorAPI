package com.caforerof.calculator.models.services;

import java.util.List;

import com.caforerof.calculator.models.entity.Operand;
import com.caforerof.calculator.models.entity.Operator;

public interface IOperatorService {
	public Operator createOperator(Long id, String value);
	public List<Operator> findBySessionId(Long id);
	public Operand execResult(Operator op, Long sessionId);
}
