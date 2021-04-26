package tn.esprit.spring.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tn.esprit.spring.dto.Mail;
import tn.esprit.spring.dto.UserDto;
import tn.esprit.spring.feigns.MailClientService;
import tn.esprit.spring.payload.requests.ForgotPasswordRequest;
import tn.esprit.spring.payload.requests.ResetPasswordRequest;
import tn.esprit.spring.payload.responses.MessageResponse;
import tn.esprit.spring.services.ForgotPasswordTokenService;
import tn.esprit.spring.services.UserService;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class ForgotPasswordController {
	@Autowired
	private ForgotPasswordTokenService forgotPasswordTokenService;
	@Autowired
	private UserService userService;
	@Autowired
	private MailClientService mailClientService;
	
	
	

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody @Valid ForgotPasswordRequest request) {
		String data = forgotPasswordTokenService.createForgotPasswordTokenForUser(request.getEmail());
		
		if(data.equals(ForgotPasswordTokenService.USER_NOT_FOUND))
			return ResponseEntity.badRequest().body("User not found with email: "+request.getEmail());
		
		//send email
		ArrayList<String> to  = new ArrayList<>();
		to.add(request.getEmail());
		Mail mail = new Mail(
				"Forgot Password",
				"Follow this link http://localhost:4200/reset-password?token="+data
				, to);
		mailClientService.sendMail(mail);
		
		return ResponseEntity.ok(new MessageResponse("Forgot password token created successfully"));
	}
	
	
	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordRequest request, @RequestParam String token) {
		String msg = forgotPasswordTokenService.validateForgotPasswordToken(token);
		if(msg.equals(ForgotPasswordTokenService.TOKEN_INVALID) || msg.equals(ForgotPasswordTokenService.TOKEN_EXPIRED))
			return ResponseEntity.badRequest().body("Token is invalid or was expired");
		
		UserDto user = forgotPasswordTokenService.getForgotPasswordToken(token).getUser();
		userService.changePassword(user.getId(), request.getPassword());
		
		return ResponseEntity.ok(new MessageResponse("Password changed successfully"));
	}
}
