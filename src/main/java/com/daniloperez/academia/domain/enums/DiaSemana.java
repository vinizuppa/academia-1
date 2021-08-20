package com.daniloperez.academia.domain.enums;

public enum DiaSemana {
	SEGUNDA(1, "Segunda-feira"),
	TERCA(2, "Terça-feira"),
	QUARTA(3, "Quarta-feira"),
	QUINTA(4, "Quinta-feira"),
	SEXTA(5, "Sexta-feira"),
	SABADO(6, "Sábado"),
	DOMINGO(7, "Domingo");
	
	private int cod;
	private String descricao;
	
	private DiaSemana(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
