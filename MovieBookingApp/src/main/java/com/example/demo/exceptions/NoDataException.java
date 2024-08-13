package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="No data fetched from database")
public class NoDataException extends RuntimeException {
	
	public NoDataException(String message) {
		super(message);
	}

}
