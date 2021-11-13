package com.daniloperez.academia.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OrderBy;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.domain.ItemScript;
import com.daniloperez.academia.domain.enums.Ativo;
import com.fasterxml.jackson.annotation.JsonFormat;


public class ScriptTreinoDTO {

	
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataCriacao;
	

	private Aluno aluno;
	

	private Instrutor instrutor;
	
	@Enumerated(EnumType.STRING)
	private Ativo ativo;
	
	
	@OrderBy("diasemana")
	private List<ItemScript> itens = new ArrayList<>();
	
	public ScriptTreinoDTO() {

	}

	public ScriptTreinoDTO(Integer id, Date dataCriacao, Aluno aluno, Instrutor instrutor , Ativo ativo) {
		super();
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.aluno = aluno;
		this.setInstrutor(instrutor);
		this.ativo = (ativo==null) ? null : ativo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public List<ItemScript> getItens() {
		return itens;
	}

	public void setItens(List<ItemScript> itens) {
		this.itens = itens;
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
		ScriptTreinoDTO other = (ScriptTreinoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
