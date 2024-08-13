package com.example.demo.controller;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.UserDTO;

@RestController
@RequestMapping("call/consumer")
@CrossOrigin(origins = "*")
public class ConsumerController {
	
	@PostMapping("/login")
	public ResponseEntity<?> consumeLogin(@RequestBody UserDTO user) throws RestClientException, Exception {
		String baseUrl = "http://localhost:8084/auth/v1/login";
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<Map<String, String>> result;
		
		try {
			result = restTemp.exchange(baseUrl,HttpMethod.POST, getHeaders(user),
					new ParameterizedTypeReference<Map<String, String>>(){});
		} catch ( Exception e ) { 
			return new ResponseEntity<String>("Login failed!", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Map<String, String>>(result.getBody(), HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> consumeSignin(@RequestBody UserDTO user) throws RestClientException, Exception {
		String baseUrl = "http://localhost:8084/auth/v1/register";
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<Map<String, String>> result;
		
		try {
			result = restTemp.exchange(baseUrl,HttpMethod.POST, getHeaders(user),
					new ParameterizedTypeReference<Map<String, String>>(){});
		} catch ( Exception e ) { 
			return new ResponseEntity<String>("Signin failed!", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Map<String, String>>(result.getBody(), HttpStatus.OK);
	}

	@PostMapping("/reset")
	public ResponseEntity<?> consumeReset(@RequestBody UserDTO user) throws RestClientException, Exception {
		String baseUrl = "http://localhost:8084/auth/v1/reset";
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<Map<String, String>> result;
		
		try {
			result = restTemp.exchange(baseUrl,HttpMethod.POST, getHeaders(user),
					new ParameterizedTypeReference<Map<String, String>>(){});
		} catch ( Exception e ) { 
			return new ResponseEntity<String>("Password Reset failed!", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Map<String, String>>(result.getBody(), HttpStatus.OK);
	}

	private static HttpEntity<UserDTO> getHeaders(UserDTO user) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<UserDTO>(user, headers);
	}
}
