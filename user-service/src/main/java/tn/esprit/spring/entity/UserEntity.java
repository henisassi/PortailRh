package tn.esprit.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import tn.esprit.spring.enums.EGender;
import tn.esprit.spring.enums.Erole;

@Entity
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@Column(unique = true)
	private String cin;
	
	private String phone;
	private String address;
	
	
	@OneToOne
	private Photo photo;
	
	@Enumerated(EnumType.STRING)
	private EGender gender;
	
	
	@Enumerated(EnumType.STRING)
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

	public UserEntity(Long id, String firstName, String lastName, String email, String password, String cin,
			String phone, String address, Photo photo, EGender gender, Erole role, Boolean enabled) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.cin = cin;
		this.phone = phone;
		this.address = address;
		this.photo = photo;
		this.gender = gender;
		this.role = role;
		this.enabled = enabled;
	}

	public UserEntity() {
		super();
	}
	
	
	

}
