package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.models.Mail;
import tn.esprit.spring.services.MailService;

@RestController
@RequestMapping("/api")
public class MailController {

	@Autowired
	private MailService mailService;
	
	@PostMapping("/mail/send")
	public ResponseEntity<?> sendMail(@RequestBody Mail mail) {		
		mailService.sendMail(mail);
		return ResponseEntity.ok("Mail Sended Successfully");
	}
}
