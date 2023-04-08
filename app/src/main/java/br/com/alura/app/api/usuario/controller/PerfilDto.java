package br.com.alura.app.api.usuario.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.app.api.usuario.model.Perfil;

public class PerfilDto {
	
	private Long id;
	
	private String nome;
	
	public PerfilDto(Perfil perfil) {
		this.id = perfil.getId();
		this.nome = perfil.getNome() ;
	}

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

	public static List<PerfilDto> converter(List<Perfil> perfis) {
		
		List<PerfilDto> perfisDto = new ArrayList<>();
		perfis.forEach(t -> perfisDto.add(new PerfilDto(t)));

		return perfisDto;
	}

}
