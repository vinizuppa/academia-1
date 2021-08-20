package com.daniloperez.academia.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.daniloperez.academia.domain.enums.BioTipo;
import com.daniloperez.academia.services.validation.AlunoInsert;
import com.fasterxml.jackson.annotation.JsonFormat;

@AlunoInsert
public class AlunoNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@NotEmpty(message="Preenchimento obrigatório") //Anotação para validação do nome da pessoa, definindo que nome é um campo de preenchimento obrigatório
	@Size(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres") //Anotação que define o tamanho minimo e máximo que pode ter o nome da pessoa
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@CPF
	private String cpf;
	@Enumerated(EnumType.STRING)
	private BioTipo biotipo;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Past
	private Date data_nasc;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data_cad;
	
	private char sexo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	@Positive(message="Peso incorreto! Insira valor maior que 0")
	private double peso;
	
	@Positive(message="Altura incorreta! Insira valor maior que 0")
	private double altura;
	
	@Positive(message="IMC incorreto! Insira valor maior que 0")
	private double imc;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String logradouro;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cep;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String telefone1;
	
	private String telefone2;
		
	private Integer cidadeId;
	
	public AlunoNewDTO() {
		
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BioTipo getBiotipo() {
		return biotipo;
	}

	public void setBiotipo(BioTipo biotipo) {
		this.biotipo = biotipo;
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
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	
	
}
