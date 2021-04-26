package tn.esprit.spring.services;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.dto.BasicUserInfoDto;
import tn.esprit.spring.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService{

	UserDto getUserById(Long userId);

	List<UserDto> getUsers();

	UserDto getUserByEmail(String email);

	void AddUser(UserDto userDto);

	void UpdateUser(UserDto userDto);

	void DeleteUser(Long userId);

	void changePassword(Long id, String password);
	
	void updateBasicUserInfo(BasicUserInfoDto basicUserInfoDto);
}
