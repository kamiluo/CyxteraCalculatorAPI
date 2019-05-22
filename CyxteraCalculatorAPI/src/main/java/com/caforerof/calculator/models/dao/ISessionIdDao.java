package com.caforerof.calculator.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.caforerof.calculator.models.entity.SessionId;


public interface ISessionIdDao extends CrudRepository<SessionId, Long>{
}
