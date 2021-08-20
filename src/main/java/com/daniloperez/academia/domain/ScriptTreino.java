package com.daniloperez.academia.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.daniloperez.academia.domain.enums.Ativo;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class ScriptTreino implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataCriacao;
	
	@ManyToOne
	@JoinColumn(name="aluno_id")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name="instrutor_id")
	private Instrutor instrutor;
	
	@Enumerated(EnumType.STRING)
	private Ativo ativo;
	
	@OneToMany(mappedBy = "id.scriptTreino")//Por ter uma classe auxiliar para criar a chave composta do ScriptTreino(que é a ScriptTreinoPK), é necessário usar esse id.script
	private Set<ItemScript> itens = new HashSet<>();
	
	public ScriptTreino() {

	}

	public ScriptTreino(Integer id, Date dataCriacao, Aluno aluno, Instrutor instrutor , Ativo ativo) {
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

	public Set<ItemScript> getItens() {
		return itens;
	}

	public void setItens(Set<ItemScript> itens) {
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
		ScriptTreino other = (ScriptTreino) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
