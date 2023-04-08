package br.com.alura.app.api.usuario.controller;

import java.util.List;

import br.com.alura.app.api.usuario.model.Usuario;

public class UsuarioDto {
	
	private String usuario;
	
	private List<PerfilDto> perfis;
	
	public UsuarioDto(Usuario usuario) {
		this.usuario = usuario.getLogin();
		this.perfis = PerfilDto.converter(usuario.getPerfis());
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public List<PerfilDto> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<PerfilDto> perfis) {
		this.perfis = perfis;
	}

}
