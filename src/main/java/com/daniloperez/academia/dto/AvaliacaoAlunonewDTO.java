package com.daniloperez.academia.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class AvaliacaoAlunonewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Positive(message="Altura incorreta! Insira valor maior que 0")
	private double altura;
	@Positive(message="Peso incorreto! Insira valor maior que 0")
	private double peso;
	@Positive(message="IMC incorreto! Insira valor maior que 0")
	private double imc;
	
	@NotEmpty(message="Preenchimento obrigatório") 
	@Size(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String obs;

	private Date data;
	
	private Integer alunoId;
	private Integer instrutorId;
	
	
	AvaliacaoAlunonewDTO(){
		
	}


	public double getAltura() {
		return altura;
	}


	public void setAltura(double altura) {
		this.altura = altura;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	public double getImc() {
		return imc;
	}


	public void setImc(double imc) {
		this.imc = imc;
	}


	public String getObs() {
		return obs;
	}


	public void setObs(String obs) {
		this.obs = obs;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public Integer getAlunoId() {
		return alunoId;
	}


	public void setAlunoId(Integer alunoId) {
		this.alunoId = alunoId;
	}


	public Integer getInstrutorId() {
		return instrutorId;
	}


	public void setInstrutorId(Integer instrutorId) {
		this.instrutorId = instrutorId;
	}

	


	
	
	
	
}
