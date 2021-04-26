package tn.esprit.spring.payload.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserPasswordRequest {

	@NotBlank(message="old password cannot be null")
	@Size(min=8,max=16,message = "old password must be equal or greater than 8 characters and less than 16 characters ")
	private String oldPassword;
	@NotBlank(message="new password cannot be null")
	@Size(min=8,max=16,message = "new password must be equal or greater than 8 characters and less than 16 characters ")
	private String newPassword;
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public ChangeUserPasswordRequest(
			@NotBlank(message = "old password cannot be null") @Size(min = 8, max = 16, message = "old password must be equal or greater than 8 characters and less than 16 characters ") String oldPassword,
			@NotBlank(message = "new password cannot be null") @Size(min = 8, max = 16, message = "new password must be equal or greater than 8 characters and less than 16 characters ") String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	public ChangeUserPasswordRequest() {
		super();
	}
	
	
}
