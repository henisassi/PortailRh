package tn.esprit.spring.dto;

import java.io.Serializable;
import java.util.Date;

public class ForgotPasswordTokenDto implements Serializable{
	
	private static final long serialVersionUID = -3084950287518830179L;

	private Long id;
	private String token;
	private UserDto user;
	private Date expiryDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public ForgotPasswordTokenDto(Long id, String token, UserDto user, Date expiryDate) {
		super();
		this.id = id;
		this.token = token;
		this.user = user;
		this.expiryDate = expiryDate;
	}
	public ForgotPasswordTokenDto() {
		super();
	}
	
	

}
