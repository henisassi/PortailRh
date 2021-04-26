package tn.esprit.spring.payload.responses;

import lombok.Data;

import tn.esprit.spring.enums.Erole;

@Data
public class UserResponse {
	
	private Long id;
	private Erole role;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Erole getRole() {
		return role;
	}
	public void setRole(Erole role) {
		this.role = role;
	}


}
