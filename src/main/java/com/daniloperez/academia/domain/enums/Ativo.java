package com.daniloperez.academia.domain.enums;

public enum Ativo {
	INATIVO(0, "Inativo"),
	ATIVO(1, "Ativo");
	
	private int cod;
	private String descricao;
	
	private Ativo(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Ativo toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for(Ativo x : Ativo.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
