package com.caforerof.calculator.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caforerof.calculator.models.services.ICyxteraLogService;

@Service
public class CyxteraExtendedLogger {

	@Autowired
	ICyxteraLogService cyxteraLogService;
	
	private static final String INFO = "INFO";
	private static final String WARNING = "WARNING";
	private static final String ERROR = "ERROR";
	
	public void info(long sessionId, String logger, String method, String message, String request) {
		cyxteraLogService.log(sessionId, logger, method, message, request, INFO);
	}

	public void info(long sessionId, String logger, String method, String message) {
		this.info(sessionId, logger, method, message, "");
	}

	public void info(String logger, String method, String message) {
		this.info(0, logger, method, message);
	}

	public void info(String logger, String message) {
		this.info(logger, "", message);
	}

	public void info(String message) {
		this.info("", message);
	}
	
	public void warn(long sessionId, String logger, String method, String message, String request) {
		cyxteraLogService.log(sessionId, logger, method, message, request, WARNING);
	}

	public void warn(long sessionId, String logger, String method, String message) {
		this.warn(sessionId, logger, method, message, "");
	}

	public void warn(String logger, String method, String message) {
		this.warn(0, logger, method, message);
	}

	public void warn(String logger, String message) {
		this.warn(logger, "", message);
	}

	public void warn(String message) {
		this.warn("", message);
	}
	
	public void error(long sessionId, String logger, String method, String message, String request) {
		cyxteraLogService.log(sessionId, logger, method, message, request, ERROR);
	}

	public void error(long sessionId, String logger, String method, String message) {
		this.error(sessionId, logger, method, message, "");
	}

	public void error(String logger, String method, String message) {
		this.error(0, logger, method, message);
	}

	public void error(String logger, String message) {
		this.error(logger, "", message);
	}

	public void error(String message) {
		this.error("", message);
	}
}
