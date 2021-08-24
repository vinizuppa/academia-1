package com.daniloperez.academia.domain.enums;

public enum Perfil {
	ADMIN(0, "ROLE_ADMIN"),
	ALUNO(1, "ROLE_ALUNO"),
	INSTRUTOR(2, "ROLE_INSTRUTOR"),
	ESTABELECIMENTO(3, "ROLE_ESTABELECIMENTO");
	private int cod;
	private String descricao;
	
	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
