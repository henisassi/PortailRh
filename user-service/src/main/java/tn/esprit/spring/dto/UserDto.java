package tn.esprit.spring.dto;

import java.io.Serializable;

import lombok.Data;
import tn.esprit.spring.entity.Photo;
import tn.esprit.spring.enums.EGender;
import tn.esprit.spring.enums.Erole;

@Data
public class UserDto implements Serializable{

	
	private static final long serialVersionUID = -7925870888599365810L;
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String cin;
	
	private String phone;
	private String address;
	
	private Photo photo;

	private EGender gender;


	private Erole role;

	private Boolean enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public UserDto(Long id, String firstName, String lastName, String email, String password, String cin, String phone,
			String address, EGender gender, Erole role, Boolean enabled) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.cin = cin;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.role = role;
		this.enabled = enabled;
	}

	public UserDto() {
		super();
	}

	
	
}
