package com.caforerof.calculator.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caforerof.calculator.models.dao.IOperandDao;
import com.caforerof.calculator.models.entity.Operand;
import com.caforerof.calculator.models.entity.SessionId;
import com.caforerof.calculator.responses.CustomErrorCalcException;

@Service
public class OperandServiceImpl implements IOperandService{

	@Autowired
	IOperandDao operandDao;
	
	@Autowired
	SessionIdServiceImpl sessionService;

	@Override
	@Transactional
	public Operand createOperand(Long id, Double value) {
		SessionId sessionId = sessionService.findById(id);
		if(sessionId == null) {
			throw new CustomErrorCalcException("El sessionId no existe.", 5);
		}
		Operand operand = new Operand();
		operand.setStatus("Activo");
		operand.setValue(value);
		operand.setCreateAt(new Date());
		operand.setSessionId(sessionId);
		operandDao.save(operand);
		return operand;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Operand> findBySessionId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatusBySessionIdActive(Long sessionId) {
		operandDao.updateStatusBySessionIdActive(sessionId);
	}

	@Override
	public List<Operand> findActiveBySessionId(Long sessionId) {
		return operandDao.findActiveBySessionId(sessionId);
	}

	@Override
	public List<Operand> findAll() {
		return operandDao.findAll();
	}

}
