package br.com.alura.app.api.hello;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hello {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ilive;
	
	private LocalDateTime data;
	
	private String message;

	public Hello() {
		this.ilive = "Yeah i live baby";
		this.data = LocalDateTime.now();
		this.message = "API is live, database is live, let`s code";
	}

	public String getIlive() {
		return ilive;
	}

	public void setIlive(String ilive) {
		this.ilive = ilive;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
