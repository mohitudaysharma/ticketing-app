package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT, reason="Object with given id already exists")
public class DataAlreadyExistsException extends RuntimeException {
	
	public DataAlreadyExistsException(String message) {
		super(message);
	}

}
