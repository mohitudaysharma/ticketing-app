package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="No such data opertaion feasible. Please recheck accuracy of data!")
public class InvalidDataOperationException extends RuntimeException {
	
	public InvalidDataOperationException(String message) {
		super(message);
	}

}
