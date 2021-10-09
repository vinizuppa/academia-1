package com.daniloperez.academia.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.daniloperez.academia.domain.AvaliacaoAluno;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AvaliacaoAlunoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@Positive(message="Altura incorreta! Insira valor maior que 0")
	private double altura;
	@Positive(message="Peso incorreto! Insira valor maior que 0")
	private double peso;
	@Positive(message="IMC incorreto! Insira valor maior que 0")
	private double imc;
	
	@NotEmpty(message="Preenchimento obrigatório") 
	@Size(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String obs;
	
	public AvaliacaoAlunoDTO() {
		
	}
	
	

	public AvaliacaoAlunoDTO(AvaliacaoAluno obj) {
		id = obj.getId();
		altura = obj.getAltura();
		peso = obj.getPeso();
		imc = obj.getImc();
		obs = obj.getObs();
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	
	

}
