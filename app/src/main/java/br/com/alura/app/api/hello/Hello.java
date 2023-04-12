package br.com.alura.app.api.hello;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Data
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
}
