package com.litmus7.training.ecommerce.backend.config.filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.litmus7.training.ecommerce.backend.dao.UserRepository;
import com.litmus7.training.ecommerce.backend.entity.AppUser;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	@Autowired
	private UserRepository userRepo;
	
	private AuthenticationManager authenticationManager;

	public CustomAuthenticationFilter(AuthenticationManager auth, UserRepository userRepo) {
		super();
		this.authenticationManager = auth;
		this.userRepo = userRepo;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			BufferedReader reader = request.getReader();
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String parsedReq = sb.toString();
			if (parsedReq != null) {
				ObjectMapper mapper = new ObjectMapper();
				AuthReq authReq = mapper.readValue(parsedReq, AuthReq.class);
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
						authReq.getUsername(), authReq.getPassword());
				return authenticationManager.authenticate(token);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new InternalAuthenticationServiceException("Failed to parse authentication request body");
		}
		return null;

		
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		User user=(User) authentication.getPrincipal();
		Algorithm algorithm= Algorithm.HMAC256("Secret-message".getBytes());
		String access_token = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 24*60*60*1000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
		String refresh_token = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 24*7*60*60*1000))
				.withIssuer(request.getRequestURL().toString())
				.sign(algorithm);
		//response.setHeader("access_token", access_token);
		//response.setHeader("refresh_token", refresh_token);
		AppUser appUser = userRepo.findByEmail(user.getUsername());
		Map<String,String> token= new HashMap<>();
		token.put("access_token", access_token);
		token.put("refresh_token", refresh_token);
		token.put("userId", appUser.getId().toString());
		token.put("name", appUser.getName());
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), token);
		//super.successfulAuthentication(request, response, chain, authentication);
	}


	public static class AuthReq {
		String username;
		String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}
}
