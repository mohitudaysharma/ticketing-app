package com.example.demo.filter;

import java.io.IOException;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTFilter extends GenericFilterBean {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		
		String authHeader = httpReq.getHeader("authorization");
		
		if ( authHeader == null || !authHeader.startsWith("Bearer") )
			throw new ServletException("Missing or invalid authentication header!");
		
		String jwtToken = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("mySecretKey").parseClaimsJws(jwtToken).getBody();
		httpReq.setAttribute("username", claims);
		chain.doFilter(req, resp);

	}
}