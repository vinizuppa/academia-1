package com.daniloperez.academia.domain.enums;


//Implementação enumerada, para ter controle total de código atribuido a cada valor da enumeração
public enum BioTipo {
	ECTOMORFO(1, "Ectomorfo"),
	MESOMORFO(2, "Mesomorfo"),
	ENDOMORFO(3, "Endomorfo");
	
	private int cod;
	private String descricao;
	
	private BioTipo(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static BioTipo toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for(BioTipo x : BioTipo.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
