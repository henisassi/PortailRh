package tn.esprit.spring.services;

import java.io.File;
import java.util.concurrent.ScheduledExecutorService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import tn.esprit.spring.models.Mail;

@Service
public class MailServiceImpl implements MailService{
	@Autowired
	private JavaMailSender emailSender;
    private Environment environment;
    private ScheduledExecutorService quickService;
    
    
    @Override
	public void sendMail(Mail mail) {		
	    try {
	    	MimeMessage message = emailSender.createMimeMessage();
	    	
	    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(environment.getProperty("spring.mail.username"));
			helper.setTo( mail.getTo().toArray(new String[0]));
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getText());
			
			for(int i=0;i<mail.getAttachements().size();i++) {
				FileSystemResource file 
				= new FileSystemResource(new File(mail.getAttachements().get(i)));
				helper.addAttachment("Attachement "+(i+1), file);
			}

			quickService.submit(new Runnable() {
				
				@Override
				public void run() {
					emailSender.send(message);
				}
			});
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
