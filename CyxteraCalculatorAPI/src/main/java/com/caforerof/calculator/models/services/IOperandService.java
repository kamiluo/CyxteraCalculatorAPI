package com.caforerof.calculator.models.services;

import java.util.List;

import com.caforerof.calculator.models.entity.Operand;

public interface IOperandService {
	public Operand createOperand(Long id, Double value);
	public List<Operand> findBySessionId(Long id);
	public void updateStatusBySessionIdActive(Long sessionId);
	public List<Operand> findActiveBySessionId(Long sessionId);
	public List<Operand> findAll();
}
