package tn.esprit.spring.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.spring.dto.Mail;

@FeignClient(name = "mail-service")
public interface MailClientService {
	
	@PostMapping("/api/mail/send")
	void sendMail(@RequestBody Mail mail);

}
