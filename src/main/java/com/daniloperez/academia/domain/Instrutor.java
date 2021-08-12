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
public class Instrutor extends Pessoa{
	private static final long serialVersionUID = 1L;
	private String numCrf;
	
	//@JsonIgnore
	//@ManyToMany(mappedBy="instrutor")
	//private List<Estabelecimento> estabelecimentos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "instrutor", cascade=CascadeType.ALL)// cascade=CascadeType.ALL serve para indicar que se for apagar o instrutor do banco deve ser apagado as avaliações dele também
	private List<AvaliacaoAluno> avaliacoes = new ArrayList<>();
	public Instrutor() {
	}
	
	public Instrutor(Integer id,String nome, String email, String cpf, Date data_nasc, Date data_cad, char sexo, String numCrf) {
		super(id, nome, email, cpf, data_nasc, data_cad, sexo);
		this.setNumCrf(numCrf);
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
	
	

	//public List<Estabelecimento> getEstabelecimentos() {
	//	return estabelecimentos;
	//}

	//public void setEstabelecimentos(List<Estabelecimento> estabelecimentos) {
	//	this.estabelecimentos = estabelecimentos;
	//}
}
