package tn.esprit.spring.payload.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import tn.esprit.spring.enums.EGender;
import tn.esprit.spring.enums.Erole;

@Data
public class AddUserRequest {
	
	@NotBlank(message="first name cannot be null")
	@Size(min=2,message = "first name must not be less than two characters")
	private String firstName;
	
	@NotBlank(message="last name cannot be null")
	@Size(min=2,message = "last name must not be less than two characters")
	private String lastName;
	
	@NotBlank(message="email cannot be null")
	@Email(message="email must be valid")
	private String email;
	
	@NotBlank(message="password cannot be null")
	@Size(min=8,max=16,message = "password must be equal or greater than 8 characters and less than 16 characters ") 
	private String password;
	
	@NotBlank(message="CIN cannot be null")
	@Size(min=8,max=8,message = "CIN must be equal to 8 characters") 
	private String cin;
	
	
	private String phone;
	private String address;
	
	private EGender gender;
	private Erole role;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
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
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public EGender getGender() {
		return gender;
	}
	public void setGender(EGender gender) {
		this.gender = gender;
	}
	public Erole getRole() {
		return role;
	}
	public void setRole(Erole role) {
		this.role = role;
	}
	public AddUserRequest(
			@NotBlank(message = "first name cannot be null") @Size(min = 2, message = "first name must not be less than two characters") String firstName,
			@NotBlank(message = "last name cannot be null") @Size(min = 2, message = "last name must not be less than two characters") String lastName,
			@NotBlank(message = "email cannot be null") @Email(message = "email must be valid") String email,
			@NotBlank(message = "password cannot be null") @Size(min = 8, max = 16, message = "password must be equal or greater than 8 characters and less than 16 characters ") String password,
			@NotBlank(message = "CIN cannot be null") @Size(min = 8, max = 8, message = "CIN must be equal to 8 characters") String cin,
			String phone, String address, EGender gender, Erole role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.cin = cin;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.role = role;
	}
	public AddUserRequest() {
		super();
	}
	
	

}
