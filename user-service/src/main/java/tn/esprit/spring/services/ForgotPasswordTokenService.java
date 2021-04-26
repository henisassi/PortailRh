package tn.esprit.spring.services;

import tn.esprit.spring.dto.ForgotPasswordTokenDto;

public interface ForgotPasswordTokenService {
	
	static final String TOKEN_INVALID = "TOKEN_INVALID";
	static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
	static final String TOKEN_VALID = "TOKEN_VALID";
	static final String USER_NOT_FOUND = "USER_NOT_FOUND";
	
	String createForgotPasswordTokenForUser(String email);
	ForgotPasswordTokenDto getForgotPasswordToken(String token);
	String validateForgotPasswordToken(String token);

}
