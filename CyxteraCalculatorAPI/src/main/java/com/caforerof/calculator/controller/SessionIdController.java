package com.caforerof.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caforerof.calculator.models.entity.SessionId;
import com.caforerof.calculator.models.services.ISessionIdService;
import com.caforerof.calculator.responses.SessionIdResponse;

@RestController
@RequestMapping("/calculatorApi")
public class SessionIdController {
	@Autowired
	ISessionIdService sessionIdService;
	
	@RequestMapping("/getSessionId")
	public SessionIdResponse getSession() {
		SessionId s = sessionIdService.createSessionId();
		SessionIdResponse sir = new SessionIdResponse();
		sir.setSessionId(s.getId());
		return sir;
	}
}
