package com.daniloperez.academia.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.daniloperez.academia.domain.enums.DiaSemana;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemScript implements Serializable{
	private static final long serialVersionUID = 1L;
 
	@JsonIgnore //Definindo para não serializar o ItemScript
	@EmbeddedId //Definindo que essa classe tem como ID o ItemScriptPK
	private ItemScriptPK id = new ItemScriptPK();
	
	private Integer qtd_rep;
	private Integer qtd_series;
	private DiaSemana diaSemana;
	
	public ItemScript() {
		
	}

	public ItemScript(ScriptTreino script, Atividade atividade, Integer qtd_rep, Integer qtd_series, DiaSemana diaSemana) {
		super();
		id.setScriptTreino(script);//Atribuindo ScriptTreino dentro do ID
		id.setAtividade(atividade);;//Atribuindo Atividade dentro do ID
		this.qtd_rep = qtd_rep;
		this.qtd_series = qtd_series;
		this.diaSemana = (diaSemana==null) ? null : diaSemana;
	}

	@JsonIgnore
	public ScriptTreino getScript() {
		return id.getScriptTreino();
	}
	
	public Atividade getAtividade() {
		return id.getAtividade();
	}
	
	
	public ItemScriptPK getId() {
		return id;
	}

	public void setId(ItemScriptPK id) {
		this.id = id;
	}

	public Integer getQtd_rep() {
		return qtd_rep;
	}

	public void setQtd_rep(Integer qtd_rep) {
		this.qtd_rep = qtd_rep;
	}

	public Integer getQtd_series() {
		return qtd_series;
	}

	public void setQtd_series(Integer qtd_series) {
		this.qtd_series = qtd_series;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemScript other = (ItemScript) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
