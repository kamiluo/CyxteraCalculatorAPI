package com.caforerof.calculator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caforerof.calculator.responses.SessionIdResponse;

@RestController
@RequestMapping("/calculatorApi")
public class SessionIdController {
	
	@RequestMapping("/getSessionId")
	public SessionIdResponse getSession() {
		SessionIdResponse sir = new SessionIdResponse();
		return sir;
	}
}
