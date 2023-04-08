package br.com.alura.app.api.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.app.api.usuario.model.Perfil;
import br.com.alura.app.api.usuario.model.Usuario;
import br.com.alura.app.api.usuario.repository.PerfilRepository;
import br.com.alura.app.api.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;

	public Usuario getUsuarioByLogin(String login) {
		
		if (login == null) throw new IllegalArgumentException();
		
		Optional<Usuario> obj = usuarioRepository.findByLogin(login);
		
		return obj.orElseThrow();
	}
	
	public Usuario getUsuarioById(Long id) {
		
		if (id == null) throw new IllegalArgumentException();
		
		Optional<Usuario> obj = usuarioRepository.findById(id);
		
		return obj.orElseThrow();
	}

	public Perfil getPerfilById(Long id) {
		
		if (id == null) throw new IllegalArgumentException();
		
		Optional<Perfil> obj = perfilRepository.findById(id);
		
		return obj.orElseThrow();
	}

	public void cadastrar(Usuario novoUsuario) {
		
		if (novoUsuario == null) throw new IllegalArgumentException();
		
		usuarioRepository.save(novoUsuario);
	}

}
