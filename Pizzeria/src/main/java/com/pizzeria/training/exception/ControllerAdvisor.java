package com.pizzeria.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdvisor {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid input provided")
	@ExceptionHandler(IllegalArgumentException.class)
	public void handleIllegalArgumentException() {
		// returning 404 error code
	}

}
