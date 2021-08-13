package com.daniloperez.academia.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Instrutor extends Usuario{
	private static final long serialVersionUID = 1L;
	private String numCrf;
	private String cpf;
	@JsonIgnore
	@ManyToMany(mappedBy="instrutores")
	private List<Estabelecimento> estabelecimentos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "instrutor", cascade=CascadeType.ALL)// cascade=CascadeType.ALL serve para indicar que se for apagar o instrutor do banco deve ser apagado as avaliações dele também
	private List<AvaliacaoAluno> avaliacoes = new ArrayList<>();
	public Instrutor() {
	}
	
	public Instrutor(Integer id,String nome, String email, String cpf, Date data_nasc, Date data_cad, char sexo, String numCrf, String senha) {
		super(id, nome, email, data_nasc, data_cad, sexo, senha);
		this.numCrf = numCrf;
		this.cpf = cpf;
	}

	public String getNumCrf() {
		return numCrf;
	}

	public void setNumCrf(String numCrf) {
		this.numCrf = numCrf;
	}

	public List<AvaliacaoAluno> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<AvaliacaoAluno> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	

	public List<Estabelecimento> getEstabelecimentos() {
		return estabelecimentos;
	}

	public void setEstabelecimentos(List<Estabelecimento> estabelecimentos) {
		this.estabelecimentos = estabelecimentos;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
