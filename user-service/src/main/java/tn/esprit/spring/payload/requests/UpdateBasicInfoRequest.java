package tn.esprit.spring.payload.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateBasicInfoRequest {


	private Long id;
	@NotBlank(message = "first name cannot be null")
	@Size(min = 2, message = "first name must not be less than two characters")
	private String firstName;

	@NotBlank(message = "last name cannot be null")
	@Size(min = 2, message = "last name must not be less than two characters")
	private String lastName;

	@NotBlank(message = "email cannot be null")
	@Email(message = "email must be valid")
	private String email;

	@NotBlank(message = "phone cannot be null")
	@Positive(message = "phone should be positive")
	private String phone;
	@NotBlank(message = "address cannot be null")
	private String address;

}

