package com.caforerof.calculator.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.caforerof.calculator.models.entity.Operand;


public interface IOperandDao extends CrudRepository<Operand, Long>{
	
	public List<Operand> findActiveBySessionId(Long id); 
}
