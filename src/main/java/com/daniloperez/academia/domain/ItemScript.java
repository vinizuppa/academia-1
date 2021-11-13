package com.daniloperez.academia.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.daniloperez.academia.domain.enums.DiaSemana;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemScript implements Serializable{
	private static final long serialVersionUID = 1L;
 
	//@JsonIgnore //Definindo para não serializar o ItemScript
	//@EmbeddedId //Definindo que essa classe tem como ID o ItemScriptPK
	//private ItemScriptPK id = new ItemScriptPK();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer qtd_rep;
	private Integer qtd_series;
	@Enumerated(EnumType.STRING)
	private DiaSemana diasemana;
	
	@ManyToOne
	@JoinColumn(name="script_id")
	private ScriptTreino scriptTreino;
	
	@ManyToOne
	@JoinColumn(name="atividade_id")
	private Atividade atividade;
	
	public ItemScript() {
		
	}

	public ItemScript(ScriptTreino script, Atividade atividade, Integer qtd_rep, Integer qtd_series, DiaSemana diasemana) {
		super();
		//id.setScriptTreino(script);//Atribuindo ScriptTreino dentro do ID
		//id.setAtividade(atividade);;//Atribuindo Atividade dentro do ID
		this.atividade = atividade;
		this.scriptTreino = script;
		this.qtd_rep = qtd_rep;
		this.qtd_series = qtd_series;
		this.diasemana = (diasemana==null) ? null : diasemana;
	}

	
	
	public ScriptTreino getScriptTreino() {
		return scriptTreino;
	}

	public void setScriptTreino(ScriptTreino scriptTreino) {
		this.scriptTreino = scriptTreino;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
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

	public DiaSemana getDiasemana() {
		return diasemana;
	}

	public void setDiaSemana(DiaSemana diasemana) {
		this.diasemana = diasemana;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ItemScript [id=" + id + ", qtd_rep=" + qtd_rep + ", qtd_series=" + qtd_series + ", diasemana="
				+ diasemana + ", scriptTreino=" + scriptTreino + ", atividade=" + atividade + "]";
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
