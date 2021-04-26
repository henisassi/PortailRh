package tn.esprit.spring.payload.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import tn.esprit.spring.enums.EGender;
import tn.esprit.spring.enums.Erole;

@Data
public class UpdateUserRequest {

	
	@NotBlank(message="first name cannot be null")
	@Size(min=2,message = "first name must not be less than two characters")
	private String firstName;
	@NotBlank(message="last name cannot be null")
	@Size(min=2,message = "last name must not be less than two characters")
	private String lastName;
	
	@NotBlank(message="email cannot be null")
	@Email(message="email must be valid")
	private String email;
	
	@NotBlank(message="CIN cannot be null")
	@Size(min=8,max=8,message = "CIN must be equal to 8 characters") 
	private String cin;
	
	private String cnss;
	private String phone;
	private String address;
	private short nbChild;
	private Boolean enabled;

	private EGender gender;



	private Erole role;
	
}
