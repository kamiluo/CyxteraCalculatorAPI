package com.caforerof.calculator.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.caforerof.calculator.models.entity.Operand;


public interface IOperandDao extends CrudRepository<Operand, Long>{

	public List<Operand> findActiveBySessionId(Long id); 
	public List<Operand> findAll(); 
	
	@Modifying
	public void updateStatusBySessionIdActive(@Param("sessionId") Long sessionId);
}
