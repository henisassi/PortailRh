package tn.esprit.spring.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.spring.payload.responses.UserResponse;

@FeignClient(name = "user-service")
public interface UsersServiceClient {

	@GetMapping("/api/users/{id}")
	UserResponse getUserById(@PathVariable Long id);
}
