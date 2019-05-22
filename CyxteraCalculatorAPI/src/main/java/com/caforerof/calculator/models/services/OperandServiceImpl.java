package com.caforerof.calculator.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caforerof.calculator.models.dao.IOperandDao;
import com.caforerof.calculator.models.dao.ISessionIdDao;
import com.caforerof.calculator.models.entity.Operand;
import com.caforerof.calculator.models.entity.SessionId;

@Service
public class OperandServiceImpl implements IOperandService{

	@Autowired
	IOperandDao operandDao;
	
	@Autowired
	ISessionIdDao sessionDao;

	@Override
	@Transactional
	public Operand createOperand(Long id, Double value) {
		SessionId sessionId = sessionDao.findById(id).orElse(null);
		Operand operand = new Operand();
		operand.setStatus("Activo");
		operand.setValue(value);
		operand.setCreateAt(new Date());
		operand.setSessionId(sessionId);
		operandDao.save(operand);
		List<Operand> operandos = sessionId.getOperands();
		for (Operand operand2 : operandos) {
			System.out.println("Id: " + operand2.getId() + 
					           " Valor: " + operand2.getValue() +
					           " Estado:" + operand2.getStatus());
		}
		return operand;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Operand> findBySessionId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
