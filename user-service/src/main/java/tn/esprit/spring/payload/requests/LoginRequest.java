package tn.esprit.spring.payload.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginRequest {
	
	@NotBlank(message = "email cannot be null")
	@Email(message = "email must be valid")
	private String email;
	@NotBlank(message="password cannot be null")
	@Size(min=8,max=16,message = "password must be equal or greater than 8 characters and less than 16 characters ")
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginRequest(
			@NotBlank(message = "email cannot be null") @Email(message = "email must be valid") String email,
			@NotBlank(message = "password cannot be null") @Size(min = 8, max = 16, message = "password must be equal or greater than 8 characters and less than 16 characters ") String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public LoginRequest() {
		super();
	}
	
	

}
