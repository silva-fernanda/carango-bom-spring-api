package br.com.alura.app.api.usuario.controller;

import java.net.URI;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.app.api.usuario.model.Usuario;
import br.com.alura.app.api.usuario.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/login/{login}")
	@ResponseBody
	public ResponseEntity<Object> getUsuarioByLogin(@PathVariable("login") String login) {
		logger.info("Usuario.getByLogin {0}", login);
		
		try {
			
			UsuarioDto usuarioDto = new UsuarioDto(usuarioService.getUsuarioByLogin(login));
			return ResponseEntity.status(HttpStatus.OK).body(usuarioDto);
			
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> getUsuarioById(@PathVariable("id") Long id) {
		logger.info("Usuario.getById {0}", id);
		try {
			
			UsuarioDto usuarioDto = new UsuarioDto(usuarioService.getUsuarioById(id));
			return ResponseEntity.status(HttpStatus.OK).body(usuarioDto);
			
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PostMapping
	public ResponseEntity<Object> cadastro(@RequestBody @Valid UsuarioForm usuarioForm, 
			BindingResult result, 
			UriComponentsBuilder uriBuilder) {
		
		/** Exemplo de requisição
		   {
			    "login":"alura1234",
			    "senha":"alura1234",
			    "perfis":[{
			        "id":1,
			        "nome":"Administrador"
			    },{
			        "id":2,
			        "nome":"Vendedor"
			    }]
			}
		 */
		
		logger.info(usuarioForm.toString());

		if (result.hasFieldErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usuarioForm);
		}
		
		try {
			
			Usuario novoUsuario = usuarioForm.toEntity(usuarioService);
			usuarioService.cadastrar(novoUsuario);
			
			URI uri = uriBuilder.path("/api/usuario/{id}").buildAndExpand(novoUsuario.getId()).toUri();
			return ResponseEntity.created(uri).body(new UsuarioDto(novoUsuario));
			
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}
