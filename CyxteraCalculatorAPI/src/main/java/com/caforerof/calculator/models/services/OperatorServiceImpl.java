package com.caforerof.calculator.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caforerof.calculator.models.dao.IOperatorDao;
import com.caforerof.calculator.models.dao.ISessionIdDao;
import com.caforerof.calculator.models.entity.Operand;
import com.caforerof.calculator.models.entity.Operator;
import com.caforerof.calculator.models.entity.SessionId;

@Service
public class OperatorServiceImpl implements IOperatorService{

	@Autowired
	IOperatorDao operatorDao;
	
	@Autowired
	ISessionIdDao sessionDao;

	@Override
	@Transactional
	public Operator createOperator(Long id, String value) {
		SessionId sessionId = sessionDao.findById(id).orElse(null);
		Operator operator = new Operator();
		operator.setStatus("Activo");
		operator.setValue(value);
		operator.setCreateAt(new Date());
		operator.setSessionId(sessionId);
		operatorDao.save(operator);
		List<Operator> operators = sessionId.getOperators();
		for (Operator oper2 : operators) {
			System.out.println("Id: " + oper2.getId() + 
					           " Valor: " + oper2.getValue() +
					           " Estado:" + oper2.getStatus());
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
	public Operand execResult(Operator op, Long id) {
		List<Operator> operActivos = operatorDao.findActiveBySessionId(id);
		for (Operator operator : operActivos) {
			System.out.println("Id: " + operator.getId() + 
			           " Valor: " + operator.getValue() +
			           " Estado:" + operator.getStatus());
		}
		return null;
	}


}
