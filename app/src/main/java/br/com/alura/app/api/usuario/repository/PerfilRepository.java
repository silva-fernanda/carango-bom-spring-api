package br.com.alura.app.api.usuario.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.app.api.usuario.model.Perfil;

@Repository
public interface PerfilRepository extends CrudRepository<Perfil, Long>{
	
	Optional<Perfil> findById(Long id);
	

}
