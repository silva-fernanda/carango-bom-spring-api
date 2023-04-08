package br.com.alura.app.api.usuario.controller;

import javax.validation.constraints.NotEmpty;

import br.com.alura.app.api.usuario.model.Perfil;
import br.com.alura.app.api.usuario.service.UsuarioService;

public class PerfilForm {
	
	@NotEmpty
	private Long id;
	
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Perfil toEntity(UsuarioService usuarioService) {
		return usuarioService.getPerfilById(this.id);
	}

	@Override
	public String toString() {
		return "PerfilForm [id=" + id + ", nome=" + nome + "]";
	}

}
