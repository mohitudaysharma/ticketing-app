package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth/v1")
@CrossOrigin("*")
public class AuthConroller {

	private Map<String, String> mapObj = new HashMap<>();
	
	@Autowired
	private UserService uService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
		if ( uService.addUser(user) != null )
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		return new ResponseEntity<String>("Registration failed!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public String generateToken(String username, String password) throws ServletException, Exception {
		String jwtToken;
		if ( username == null || password == null )
			throw new ServletException("Please enter valid credentials!");
		if ( uService.LoginUser(username, password)) {
			jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
						.setExpiration(new Date(System.currentTimeMillis()+60000000))
						.signWith(SignatureAlgorithm.HS256, "mySecretKey").compact();
			return jwtToken;
		}
		throw new ServletException("Invalid credentials!");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		try {
			String jwtToken = generateToken(user.getUsername(), user.getPassword());
			mapObj.put("Message","User successfully logged in.");
			mapObj.put("Token", jwtToken);
		} catch ( Exception e ) {
			mapObj.put("Message","User not logged in.");
			mapObj.put("Token", null);
			return new ResponseEntity<>(mapObj, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(mapObj, HttpStatus.OK);
	}
	
	@PostMapping("/reset")
	public ResponseEntity<?> resetUser(@RequestBody User user) {
		String msg = "Password reset failed! Please recheck inputs!";
		if ( uService.resetPassword(user.getPassword(), user.getUsername(), user.getAnswer()) )
			msg = " Password reset successful";
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		
	}
}
