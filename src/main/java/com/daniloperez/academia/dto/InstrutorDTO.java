package com.daniloperez.academia.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.services.validation.InstrutorUpdate;

@InstrutorUpdate
public class InstrutorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório") //Anotação para validação do nome da pessoa, definindo que nome é um campo de preenchimento obrigatório
	@Size(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres") //Anotação que define o tamanho minimo e máximo que pode ter o nome da pessoa
	private String nome;
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String numCrf;
	
	private char sexo;
	
	public InstrutorDTO() {
		
	}
	
	public InstrutorDTO(Instrutor obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		numCrf = obj.getNumCrf();
		sexo = obj.getSexo();
		senha = obj.getSenha();
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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getNumCrf() {
		return numCrf;
	}

	public void setNumCrf(String numCrf) {
		this.numCrf = numCrf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
