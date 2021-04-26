package tn.esprit.spring.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import tn.esprit.spring.feigns.UsersServiceClient;
import tn.esprit.spring.payload.responses.UserResponse;

public class AuthorizationFilter extends BasicAuthenticationFilter  {
	
	@Autowired
	private Environment environment;
	//@Autowired
	//private UsersServiceClient usersServiceClient;
	
	public AuthorizationFilter(AuthenticationManager authenticationManager, Environment environment) {
		super(authenticationManager);
		this.environment = environment;
		//this.usersServiceClient = usersServiceClient;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)

			throws IOException, ServletException {
		String authorizationHeader = request.getHeader(environment.getProperty("authorization.token.header.name"));

		if (authorizationHeader == null
				|| !authorizationHeader.startsWith(environment.getProperty("authorization.token.header.prefix"))) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);

	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(environment.getProperty("authorization.token.header.name"));

		if (authorizationHeader == null) {
			return null;
		}
		String token = authorizationHeader.replace(environment.getProperty("authorization.token.header.prefix"), "");

		String id = Jwts.parser().setSigningKey(environment.getProperty("token.secret")).parseClaimsJws(token).getBody()
				.getSubject();

		if (id == null) {
			return null;
		}
		
		//UserResponse response = usersServiceClient.getUserById(Long.parseLong(id));
		List<GrantedAuthority> authorities = new ArrayList<>();
		//authorities.add(new SimpleGrantedAuthority(response.getRole().name()));
		return new UsernamePasswordAuthenticationToken(id, null, authorities);
	}

	
	
}
