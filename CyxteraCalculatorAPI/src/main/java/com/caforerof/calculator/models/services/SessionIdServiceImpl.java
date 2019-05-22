package com.caforerof.calculator.models.services;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caforerof.calculator.models.dao.ISessionIdDao;
import com.caforerof.calculator.models.entity.SessionId;

@Service
public class SessionIdServiceImpl implements ISessionIdService{

	@Autowired
	ISessionIdDao sessionDao;
	
	@Override
	@Transactional
	public SessionId createSessionId() throws SQLException {
		SessionId sessionId = new SessionId();
		sessionId.setStatus("Activo");
		sessionId.setCreateAt(new Date());
		sessionDao.save(sessionId);
		return sessionId;
	}

	@Override
	@Transactional(readOnly = true)
	public SessionId findById(Long id) {
		// TODO Auto-generated method stub
		SessionId sessionId =sessionDao.findById(id).orElse(null);
		return sessionId;
	}

	@Override
	public List<SessionId> findAll() {
		return sessionDao.findAll();
	}

}
