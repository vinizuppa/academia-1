package com.daniloperez.academia.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.daniloperez.academia.domain.enums.BioTipo;

@Entity
public class Aluno extends Pessoa{
	private static final long serialVersionUID = 1L;

	private double peso;
	private double altura;
	@Enumerated(EnumType.STRING)
	private BioTipo biotipo;
	private double imc;
	
	public Aluno() {
	}

	public Aluno(Integer id,BioTipo biotipo, String nome, String cpf, Date data_nasc, Date data_cad, char sexo, double peso, double altura, double imc) {
		super(id, nome, cpf, data_nasc, data_cad, sexo);
		this.biotipo = (biotipo==null) ? null : biotipo;
		this.peso = peso;
		this.altura = altura;
		this.imc = imc;
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
	
	
	
	
}
