package tn.esprit.spring.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Mail {

	private String subject;
	private String text;
	private List<String> to = new ArrayList<>();
	private List<String> attachements = new ArrayList<>();
	
	public Mail() {
		super();
	}
	
	
	public Mail(String subject, String text, List<String> to) {
		this.subject = subject;
		this.text = text;
		this.to = to;
	}

	public Mail(String subject, String text, List<String> to, List<String> attachements) {
		this.subject = subject;
		this.text = text;
		this.to = to;
		this.attachements = attachements;
	}
}
