package tn.esprit.spring.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import tn.esprit.spring.dto.BasicUserInfoDto;
import tn.esprit.spring.dto.UserDto;
import tn.esprit.spring.entity.Photo;
import tn.esprit.spring.payload.requests.AddUserRequest;
import tn.esprit.spring.payload.requests.ChangeUserPasswordRequest;
import tn.esprit.spring.payload.requests.UpdateBasicInfoRequest;
import tn.esprit.spring.payload.requests.UpdateUserRequest;
import tn.esprit.spring.payload.responses.MessageResponse;
import tn.esprit.spring.payload.responses.UserResponse;
import tn.esprit.spring.services.PhotoService;
import tn.esprit.spring.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PhotoService photoService;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@GetMapping("/{id}")
	public UserResponse getUserById(@PathVariable Long id) {
		UserResponse response = mapper.map(userService.getUserById(id), UserResponse.class);
		return response;
	}

	@GetMapping({ "/", "" })
	public ResponseEntity<?> getUsers() {
		List<UserResponse> response = Arrays.asList(mapper.map(userService.getUsers(), UserResponse[].class));
		return ResponseEntity.ok(response);
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<?> addUser(@RequestBody @Valid AddUserRequest request) {
		UserDto userDto = mapper.map(request, UserDto.class);
		userService.AddUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("... User created successfully ... ");
	}

	@PutMapping({ "/", "" })
	public ResponseEntity<?> updateUser(@RequestBody @Valid UpdateUserRequest request) {
		UserDto userDto = mapper.map(request, UserDto.class);
		userService.UpdateUser(userDto);
		return ResponseEntity.ok("... User updated successfully ... ");
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		userService.DeleteUser(id);
		return ResponseEntity.ok("... User deleted successfully ... ");
	}
	
	@PutMapping(path = "/{id}/change-photo", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> addPhoto(@PathVariable Long id, @RequestParam("photo") MultipartFile photo)
			throws IOException {
		Photo pht = photoService.addPhoto(photo);
		UserDto userDto = userService.getUserById(id);
		userDto.setPhoto(pht);
		userService.UpdateUser(userDto);
		return ResponseEntity.ok(new MessageResponse("User photo uploaded successfully"));
	}
	
	
	@GetMapping(value = "/{id}/display-photo")
	public void getUserPhoto(HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
		UserDto userDto = userService.getUserById(id);
		Photo photo = userDto.getPhoto();

		if (photo != null) {
			response.setContentType(photo.getFileType());
			InputStream inputStream = new ByteArrayInputStream(photo.getData());
			IOUtils.copy(inputStream, response.getOutputStream());
		}

	}
	
	@PutMapping("/{id}/change-password")
	public ResponseEntity<?> changeUserPassword(@PathVariable Long id, @Valid @RequestBody ChangeUserPasswordRequest request) {
		UserDto userDto = userService.getUserById(id);
		if (encoder.matches(request.getOldPassword(), userDto.getPassword())) {
			userService.changePassword(id, request.getNewPassword());
			return ResponseEntity.ok(new MessageResponse("User password updated successfully"));
		}
		return ResponseEntity.badRequest().body(new MessageResponse("Old password doesn't match"));

	}
	
	@PutMapping("/update-Basic-Info")
	public ResponseEntity<?> UpdateBasicUserInfo(@Valid @RequestBody UpdateBasicInfoRequest request) {
		BasicUserInfoDto basicUserInfoDto = mapper.map(request, BasicUserInfoDto.class);
		userService.updateBasicUserInfo(basicUserInfoDto);
		return ResponseEntity.ok(new MessageResponse("User updated basic info successfully"));
	}
}
