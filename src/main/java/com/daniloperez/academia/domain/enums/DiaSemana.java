package com.daniloperez.academia.domain.enums;


public enum DiaSemana {
	SEGUNDA(0, "Segunda"),
	TERCA(1, "Terca"),
	QUARTA(2, "Quarta"),
	QUINTA(3, "Quinta"),
	SEXTA(4, "Sexta"),
	SABADO(5, "Sabado"),
	DOMINGO(6, "Domingo");
	private int cod;
	private String descricao;
	
	private DiaSemana(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static DiaSemana toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for(DiaSemana x : DiaSemana.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
