package com.caforerof.calculator.models.services;

import java.util.List;

import com.caforerof.calculator.models.entity.CyxteraLog;

public interface ICyxteraLogService {
	public CyxteraLog log(long sessionId, String logger, String method, String message, String request, String severity);
	public List<CyxteraLog> findBySessionId(Long id);
	public List<CyxteraLog> findAll();
}
