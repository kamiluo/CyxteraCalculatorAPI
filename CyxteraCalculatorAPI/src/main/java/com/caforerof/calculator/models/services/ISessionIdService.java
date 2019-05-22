package com.caforerof.calculator.models.services;

import com.caforerof.calculator.models.entity.SessionId;

public interface ISessionIdService {
	public SessionId createSessionId();
	public SessionId findById(Long id);
}
