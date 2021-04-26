package tn.esprit.spring.models;

import java.util.ArrayList;
import java.util.List;

public class Mail {

	private String subject;
	private String text;
	private List<String> to = new ArrayList<>();
	private List<String> attachements = new ArrayList<>();
	private int id;
	
	
	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public List<String> getTo() {
		return to;
	}


	public void setTo(List<String> to) {
		this.to = to;
	}


	public List<String> getAttachements() {
		return attachements;
	}


	public void setAttachements(List<String> attachements) {
		this.attachements = attachements;
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


	public Mail() {
		super();
	}

	
	
}
