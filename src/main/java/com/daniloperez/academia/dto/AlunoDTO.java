package com.daniloperez.academia.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.domain.enums.BioTipo;
import com.daniloperez.academia.services.validation.AlunoUpdate;

@AlunoUpdate
public class AlunoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório") //Anotação para validação do nome da pessoa, definindo que nome é um campo de preenchimento obrigatório
	@Size(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres") //Anotação que define o tamanho minimo e máximo que pode ter o nome da pessoa
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	@Enumerated(EnumType.STRING)
	private BioTipo biotipo;
	
	private char sexo;
	
	@Positive(message="Peso incorreto! Insira valor maior que 0")
	private double peso;
	
	@Positive(message="Altura incorreta! Insira valor maior que 0")
	private double altura;
	
	@Positive(message="IMC incorreto! Insira valor maior que 0")
	private double imc;
	
	public AlunoDTO() {
		
	}
	
	public AlunoDTO(Aluno obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		biotipo = obj.getBiotipo();
		sexo = obj.getSexo();
		peso = obj.getPeso();
		altura = obj.getAltura();
		imc = obj.getImc();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BioTipo getBiotipo() {
		return biotipo;
	}

	public void setBiotipo(BioTipo biotipo) {
		this.biotipo = biotipo;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
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

	public double getImc() {
		return imc;
	}

	public void setImc(double imc) {
		this.imc = imc;
	}

}
