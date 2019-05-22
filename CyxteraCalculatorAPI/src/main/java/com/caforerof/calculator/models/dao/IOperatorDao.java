package com.caforerof.calculator.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.caforerof.calculator.models.entity.Operator;


public interface IOperatorDao extends CrudRepository<Operator, Long>{
	public List<Operator> findActiveBySessionId(@Param("sessionId") Long sessionId);
	public List<Operator> findAll();
	
	@Modifying
	public void updateStatusBySessionIdActive(@Param("sessionId") Long sessionId);
}
