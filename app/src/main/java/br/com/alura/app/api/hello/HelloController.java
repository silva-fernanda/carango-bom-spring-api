package br.com.alura.app.api.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
	
	@Autowired
	private HelloRepository helloRepositoryl;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<Object> iLive() {
		
		Hello hello = new Hello();
		helloRepositoryl.save(hello);
		
		return ResponseEntity.status(HttpStatus.OK).body(hello);
	}
}
