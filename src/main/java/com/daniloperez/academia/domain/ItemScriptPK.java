package com.daniloperez.academia.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//Classe auxiliar
@Embeddable
public class ItemScriptPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="script_id")
	private ScriptTreino scriptTreino;
	
	@ManyToOne
	@JoinColumn(name="atividade_id")
	private Atividade atividade;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atividade == null) ? 0 : atividade.hashCode());
		result = prime * result + ((scriptTreino == null) ? 0 : scriptTreino.hashCode());
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
		ItemScriptPK other = (ItemScriptPK) obj;
		if (atividade == null) {
			if (other.atividade != null)
				return false;
		} else if (!atividade.equals(other.atividade))
			return false;
		if (scriptTreino == null) {
			if (other.scriptTreino != null)
				return false;
		} else if (!scriptTreino.equals(other.scriptTreino))
			return false;
		return true;
	}
	
	
}
