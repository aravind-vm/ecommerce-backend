package com.litmus7.training.ecommerce.backend.config.filters;

import static java.util.Arrays.stream;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.Arrays.stream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAthorizationFilter extends OncePerRequestFilter{
	private Logger logger= LoggerFactory.getLogger(CustomAthorizationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/login")) {
			filterChain.doFilter(request, response);
		}
		else {
			String authorizationHeader=request.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);
			if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
				try {
					String token=authorizationHeader.substring("Bearer ".length());
					Algorithm algorithm= Algorithm.HMAC256("Secret-message".getBytes());
					JWTVerifier verifier= JWT.require(algorithm).build();
					DecodedJWT decodedJwt=verifier.verify(token);
					String username= decodedJwt.getSubject();
					String []roles=decodedJwt.getClaim("roles").asArray(String.class);
					Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
					stream(roles).forEach(role->{
						authorities.add(new SimpleGrantedAuthority(role));
					});
					UsernamePasswordAuthenticationToken usernamePasswordAuthToken=new 
							UsernamePasswordAuthenticationToken(username, null, authorities);
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
					filterChain.doFilter(request, response);
				} catch (Exception e) {
					logger.error("Error: {}",e.getMessage());
					response.setHeader("Error", e.getMessage());
					response.setStatus(HttpStatus.FORBIDDEN.value());
					//response.sendError(HttpStatus.FORBIDDEN.value());
					Map<String,String> error= new HashMap<>();
					error.put("error_message", e.getMessage());
					
					response.setContentType("application/json");
					new ObjectMapper().writeValue(response.getOutputStream(), error);
				} 
			}
			else {
				filterChain.doFilter(request, response);
			}
		}
	}

}
