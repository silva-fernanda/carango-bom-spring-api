package br.com.alura.app.api.usuario.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.app.api.usuario.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	Optional<Usuario> findByLogin(String login);
	

}
