package tn.esprit.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.esprit.spring.dto.UserDto;
import tn.esprit.spring.payload.requests.LoginRequest;
import tn.esprit.spring.services.UserService;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	private UserService userService;
	@Autowired
	private Environment environment;
	
	
	
	public AuthenticationFilter(UserService userService, Environment environment,
			AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.environment = environment;
		super.setAuthenticationManager(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			LoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// session
		String userName = ((User) authResult.getPrincipal()).getUsername(); // User de spring security
		// BD
		UserDto userDto = userService.getUserByEmail(userName);
		// JWT
        String token = Jwts.builder()
        		.setSubject(userDto.getId().toString())
        		.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
        		.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
        		.compact();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(	"{\"id\":\""+userDto.getId()+"\",\"role\":\""+userDto.getRole()+"\",\"token\":\""+token+"\"}");
        response.addHeader("token", token);
        response.addHeader("userId",userDto.getId().toString());
        
	}
 
}
