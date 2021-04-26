package tn.esprit.spring.payload.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ResetPasswordRequest {
	@NotBlank(message="password cannot be null")
	@Size(min=8,max=16,message = "password must be equal or greater than 8 characters and less than 16 characters ")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ResetPasswordRequest(
			@NotBlank(message = "password cannot be null") @Size(min = 8, max = 16, message = "password must be equal or greater than 8 characters and less than 16 characters ") String password) {
		super();
		this.password = password;
	}

	public ResetPasswordRequest() {
		super();
	}
	
	

}
