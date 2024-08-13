package com.example.demo.utility;

import org.springframework.http.HttpHeaders;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenUtility {

	public static String getUserId(HttpHeaders header) {
		String jwt = header.get("Authorization").get(0).substring(7);
		Claims claim = Jwts.parser().setSigningKey("mySecretKey").parseClaimsJws(jwt).getBody();
		String username = claim.getSubject();
		return username;
	}
}
