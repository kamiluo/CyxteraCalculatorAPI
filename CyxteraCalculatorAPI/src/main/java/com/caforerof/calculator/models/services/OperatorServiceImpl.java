package com.caforerof.calculator.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caforerof.calculator.models.dao.IOperatorDao;
import com.caforerof.calculator.models.entity.Operand;
import com.caforerof.calculator.models.entity.Operator;
import com.caforerof.calculator.models.entity.SessionId;
import com.caforerof.calculator.responses.CustomErrorCalcException;

@Service
public class OperatorServiceImpl implements IOperatorService {

	@Autowired
	IOperatorDao operatorDao;

	@Autowired
	IOperandService operandService;

	@Autowired
	ISessionIdService sessionService;

	@Override
	@Transactional
	public Operator createOperator(Long id, String value) {
		SessionId sessionId = sessionService.findById(id);
		List<Operator> operActives = this.findActiveBySessionId(id);
		Operator operator = new Operator();
		if (operActives.size() < 1) {
			operator.setStatus("Activo");
			operator.setValue(value);
			operator.setCreateAt(new Date());
			operator.setSessionId(sessionId);
			operatorDao.save(operator);
		} else if (operActives.size() == 1) {
			operActives.get(0).setValue(value);
			// Se actualiza el operando existente con el valor que llega.
			operator = operatorDao.save(operActives.get(0));
		} else {
			System.out.println("Error interno, por favor cree una nueva sesión");
		}
		return operator;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Operator> findBySessionId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	@Modifying
	public Operand execResult(Operator op, Long id) {
		List<Operator> opersActive = findActiveBySessionId(id);
		List<Operand> operandsActive = operandService.findActiveBySessionId(id);
		String operType;
		Operand resultOperand = null;
		
		if (operandsActive.size() < 1) {
			throw new CustomErrorCalcException("No hay operandos por favor inserte al menos uno.", 1);
		}
		
		if (opersActive.size() > 1) {
			throw new CustomErrorCalcException("Hay más de un operador activo.", 2);
		} else if (opersActive.size() < 1) {
			throw new CustomErrorCalcException("No hay operadores.", 3);
		} else {
			operType = opersActive.get(0).getValue();
			UtilService util = new UtilService();
			Double result = util.operation(operandsActive, operType);
			System.out.println("Resultado = " + result);
			this.updateStatusBySessionIdActive(id);
			operandService.updateStatusBySessionIdActive(id);
			resultOperand = operandService.createOperand(id, result);
			
		}
		return resultOperand;
	}

	@Override
	public void updateStatusBySessionIdActive(Long sessionId) {
		operatorDao.updateStatusBySessionIdActive(sessionId);
	}

	@Override
	public List<Operator> findActiveBySessionId(Long sessionId) {
		return operatorDao.findActiveBySessionId(sessionId);
	}

	@Override
	public List<Operator> findAll() {
		return operatorDao.findAll();
	}

}
