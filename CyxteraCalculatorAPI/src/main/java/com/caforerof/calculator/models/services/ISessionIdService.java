package com.caforerof.calculator.models.services;

import java.sql.SQLException;
import java.util.List;

import com.caforerof.calculator.models.entity.SessionId;

public interface ISessionIdService {
	public SessionId createSessionId() throws SQLException ;
	public SessionId findById(Long id);
	public List<SessionId> findAll();
}
