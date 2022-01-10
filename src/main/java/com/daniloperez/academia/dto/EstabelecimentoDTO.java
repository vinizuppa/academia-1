package com.daniloperez.academia.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;


public class EstabelecimentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@NotEmpty(message="Preenchimento obrigatório") //Anotação para validação do nome da pessoa, definindo que nome é um campo de preenchimento obrigatório
	@Size(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres") //Anotação que define o tamanho minimo e máximo que pode ter o nome da pessoa
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@CNPJ
	private String cnpj;
	@Past
	private Date data_nasc;
	
	private Date data_cad;
	
	private char sexo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	private String razao_social;
	
	private Integer categoriaId;
	
	private Integer planoId;
	
	public EstabelecimentoDTO() {
		
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}

	public Date getData_cad() {
		return data_cad;
	}

	public void setData_cad(Date data_cad) {
		this.data_cad = data_cad;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Integer getPlanoId() {
		return planoId;
	}

	public void setPlanoId(Integer planoId) {
		this.planoId = planoId;
	}

}
