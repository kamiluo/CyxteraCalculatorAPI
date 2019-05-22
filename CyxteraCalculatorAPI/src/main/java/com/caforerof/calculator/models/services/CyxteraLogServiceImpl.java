package com.caforerof.calculator.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caforerof.calculator.models.dao.ICyxteraLogDao;
import com.caforerof.calculator.models.entity.CyxteraLog;

@Service
public class CyxteraLogServiceImpl implements ICyxteraLogService{

	@Autowired
	ICyxteraLogDao cyxteraLogDao;
	
	@Override
	@Transactional
	public CyxteraLog log(long sessionId, String logger, String method, String message, String request, String severity) {
		CyxteraLog log = new CyxteraLog();
		
		log.setDated(new Date());
		log.setSessionId(sessionId);
		log.setLogger(logger);
		log.setMethod(method);
		log.setMessage(message);
		log.setRequest(request);
		log.setSeverity(severity);
		
		cyxteraLogDao.save(log);
		return log;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CyxteraLog> findBySessionId(Long id) {
		List<CyxteraLog> cyxteraLog =cyxteraLogDao.findBySessionId(id);
		return cyxteraLog;
	}

	@Override
	public List<CyxteraLog> findAll() {
		List<CyxteraLog> cyxteraLog = (List<CyxteraLog>) cyxteraLogDao.findAll();
		return cyxteraLog;
	}

}
