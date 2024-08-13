package com.example.demo.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Object> responsify(String msg, HttpStatus code, Object load) {
		
		Map<String, Object> mapObj = new HashMap<String, Object>();
		
		mapObj.put("Message", msg);
		mapObj.put("Status Code", code);
		mapObj.put("Payload", load);
		
		return new ResponseEntity<Object>(mapObj, code);
	}
}