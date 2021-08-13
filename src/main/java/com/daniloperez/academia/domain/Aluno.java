package com.daniloperez.academia.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import com.daniloperez.academia.domain.enums.BioTipo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Aluno extends Usuario{
	private static final long serialVersionUID = 1L;

	private double peso;
	private double altura;
	private String cpf;
	@Enumerated(EnumType.STRING)
	private BioTipo biotipo;
	private double imc;
	
	@JsonIgnore
	@OneToMany(mappedBy = "aluno", cascade=CascadeType.ALL)// cascade=CascadeType.ALL serve para indicar que se for apagar o aluno do banco deve ser apagado as avaliações dele também
	private List<AvaliacaoAluno> avaliacoes = new ArrayList<>();
	
	public Aluno() {
	}

	public Aluno(Integer id,BioTipo biotipo, String nome, String email, String cpf, Date data_nasc, Date data_cad, char sexo, double peso, double altura, double imc, String senha) {
		super(id, nome, email, data_nasc, data_cad, sexo, senha);
		this.biotipo = (biotipo==null) ? null : biotipo;
		this.peso = peso;
		this.altura = altura;
		this.imc = imc;
		this.cpf = cpf;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public BioTipo getBiotipo() {
		return biotipo;
	}

	public void setBiotipo(BioTipo biotipo) {
		this.biotipo = biotipo;
	}

	public double getImc() {
		return imc;
	}

	public void setImc(double imc) {
		this.imc = imc;
	}

	public List<AvaliacaoAluno> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<AvaliacaoAluno> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
	
}
