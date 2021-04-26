package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dto.BasicUserInfoDto;
import tn.esprit.spring.dto.UserDto;
import tn.esprit.spring.entity.UserEntity;
import tn.esprit.spring.repository.ForgotPasswordTokenRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ForgotPasswordTokenRepository forgotPasswordTokenRepository;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Override
	public UserDto getUserById(Long userId) {
		UserEntity user = userRepo.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
		return mapper.map(user, UserDto.class);
	}
	
	@Override
	public List<UserDto> getUsers() {
		List<UserEntity> users = userRepo.findAll();
		return Arrays.asList(mapper.map(users, UserDto[].class));
	}
	
	@Override
	public void AddUser(UserDto userDto) {
		UserEntity user = mapper.map(userDto, UserEntity.class);	
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(true);
		userRepo.save(user);
	}
	
	@Override
	public void UpdateUser(UserDto userDto) {
		UserEntity user = userRepo.findById(userDto.getId())
				.orElseThrow(() -> new NoSuchElementException("User not found with ID:" + userDto.getId()));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setCin(userDto.getCin());
		user.setPhone(userDto.getPhone());
		user.setAddress(userDto.getAddress());
		user.setGender(userDto.getGender());
		user.setRole(userDto.getRole());
		user.setPhoto(userDto.getPhoto());
		user.setEnabled(userDto.getEnabled());
		userRepo.save(user);
	}
	
	@Override
	public void DeleteUser(Long userId) {
		if (!userRepo.existsById(userId)) {
			throw new NoSuchElementException("User not found with ID: " + userId);
		}
		userRepo.deleteById(userId);
	}
	
	@Override
	public UserDto getUserByEmail(String email) {
		UserEntity user = userRepo.findByEmail(email)
				.orElseThrow(() -> new NoSuchElementException("User not found with EMAIL:" + email));
		return mapper.map(user, UserDto.class);
	}
	
	
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.findByEmail(username)
				.orElseThrow(() -> new NoSuchElementException("User not found with EMAIL:" + username));

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
		return new User(user.getEmail(), user.getPassword(),
				user.getEnabled(), true, true, true, authorities);
	}
	
	
	
	
	
	
	
	@Override
	public void changePassword(Long id, String password) {
		UserEntity user = userRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("User not found with ID: " + id));
		user.setPassword(encoder.encode(password));
		userRepo.save(user);
	}
	
	
	@Override
	public void updateBasicUserInfo(BasicUserInfoDto basicUserInfoDto) {
		UserEntity user = userRepo.findById(basicUserInfoDto.getId())
				.orElseThrow(() -> new NoSuchElementException("User not found with ID: " + basicUserInfoDto.getId()));
		user.setFirstName(basicUserInfoDto.getFirstName());
		user.setLastName(basicUserInfoDto.getLastName());
		user.setEmail(basicUserInfoDto.getEmail());
		user.setPhone(basicUserInfoDto.getPhone());
		user.setAddress(basicUserInfoDto.getAddress());
		userRepo.save(user);
	}


}
