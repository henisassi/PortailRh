package tn.esprit.spring.services;

import java.util.Calendar;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dto.ForgotPasswordTokenDto;
import tn.esprit.spring.entity.ForgotPasswordToken;
import tn.esprit.spring.entity.UserEntity;
import tn.esprit.spring.repository.ForgotPasswordTokenRepository;
import tn.esprit.spring.repository.UserRepository;
@Service
public class ForgotPasswordTokenImpl implements ForgotPasswordTokenService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ForgotPasswordTokenRepository forgotPasswordTokenRepository;
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public String createForgotPasswordTokenForUser(String email) {
		UserEntity user = userRepository.findByEmail(email).orElse(null);
		if(user == null)
			return USER_NOT_FOUND;
		
		String token = UUID.randomUUID().toString();
		ForgotPasswordToken forgotPasswordToken = forgotPasswordTokenRepository.findByUser_Id(user.getId())
				.orElse(new ForgotPasswordToken(token, user));
		forgotPasswordToken.updateToken(token);
		forgotPasswordTokenRepository.save(forgotPasswordToken);
		return token;
	}

	@Override
	public ForgotPasswordTokenDto getForgotPasswordToken(String token) {
		return mapper.map(forgotPasswordTokenRepository.findByToken(token).orElse(null), ForgotPasswordTokenDto.class);
	}
	
	@Override
	public String validateForgotPasswordToken(String token) {
		ForgotPasswordToken forgotPasswordToken = forgotPasswordTokenRepository.findByToken(token).orElse(null);
		if (forgotPasswordToken == null) {
			return TOKEN_INVALID;
		}

		Calendar cal = Calendar.getInstance();
		if ((forgotPasswordToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			forgotPasswordTokenRepository.delete(forgotPasswordToken);
			return TOKEN_EXPIRED;
		}

		return TOKEN_VALID;
	}

}
