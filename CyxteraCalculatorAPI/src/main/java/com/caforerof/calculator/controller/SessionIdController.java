package com.caforerof.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caforerof.calculator.models.entity.SessionId;
import com.caforerof.calculator.models.services.ISessionIdService;
import com.caforerof.calculator.responses.SessionIdResponse;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/calculatorApi")
public class SessionIdController {
	@Autowired
	ISessionIdService sessionIdService;

	@RequestMapping("/getSessionId")
	public SessionIdResponse getSession() {
		SessionIdResponse sir = new SessionIdResponse();		
		try {
			SessionId s = sessionIdService.createSessionId();
			sir.setSessionId(s.getId());
			sir.setMessage("Id creado correctamente.");
			sir.setResponseCode(200);			
			return sir;
		} catch (Exception e) {
			sir.setMessage("Error al crear ID");
			sir.setResponseCode(500);
			return sir;
		}
	}
}
