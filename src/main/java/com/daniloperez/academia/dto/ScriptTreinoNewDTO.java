package com.daniloperez.academia.dto;

import java.io.Serializable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.daniloperez.academia.domain.enums.Ativo;



public class ScriptTreinoNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	private Ativo ativo;
	
	public ScriptTreinoNewDTO() {
		
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}
	
	
}
