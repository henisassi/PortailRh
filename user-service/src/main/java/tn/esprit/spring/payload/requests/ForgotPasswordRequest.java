package tn.esprit.spring.payload.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ForgotPasswordRequest {

	@NotBlank(message = "email cannot be null")
	@Email(message = "email must be valid")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ForgotPasswordRequest(
			@NotBlank(message = "email cannot be null") @Email(message = "email must be valid") String email) {
		super();
		this.email = email;
	}

	public ForgotPasswordRequest() {
		super();
	}
	
	
}
