package com.daniloperez.academia.dto;

import java.util.Set;

import com.daniloperez.academia.domain.enums.Perfil;

public class PerfilDTO {
	Set<Perfil> perfis;

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}  
}
