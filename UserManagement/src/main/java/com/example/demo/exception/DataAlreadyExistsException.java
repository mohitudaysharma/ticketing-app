package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Data already exists!")
public class DataAlreadyExistsException extends RuntimeException {
	
	public DataAlreadyExistsException( String message ) {
		super(message);
	}

}
