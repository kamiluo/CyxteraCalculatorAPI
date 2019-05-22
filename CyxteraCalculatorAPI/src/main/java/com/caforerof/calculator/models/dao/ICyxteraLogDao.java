package com.caforerof.calculator.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.caforerof.calculator.models.entity.CyxteraLog;


public interface ICyxteraLogDao extends CrudRepository<CyxteraLog, Long>{
	public List<CyxteraLog> findBySessionId(Long id);
}
