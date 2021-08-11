package com.daniloperez.academia.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Estabelecimento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome_fantasia;
	private String razao_social;
	private String cnpj;
	private Date data_fundacao;
	private Date data_cadastro;
	
	@ManyToMany
	@JoinTable(name = "ESTABELECIMENTO_CATEGORIA",
		joinColumns = @JoinColumn(name = "estabelecimento_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private List<Categoria> categorias = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "ESTABELECIMENTO_INSTRUTOR",
		joinColumns = @JoinColumn(name = "estabelecimento_id"),
		inverseJoinColumns = @JoinColumn(name = "instrutor_id")
	)
	private List<Instrutor> instrutores = new ArrayList<>();
	
	public Estabelecimento() {
		
	}

	public Estabelecimento(Integer id, String nome_fantasia, String razao_social, String cnpj, Date data_fundacao,
			Date data_cadastro) {
		super();
		this.id = id;
		this.nome_fantasia = nome_fantasia;
		this.razao_social = razao_social;
		this.cnpj = cnpj;
		this.data_fundacao = data_fundacao;
		this.data_cadastro = data_cadastro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getData_fundacao() {
		return data_fundacao;
	}

	public void setData_fundacao(Date data_fundacao) {
		this.data_fundacao = data_fundacao;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Instrutor> getInstrutores() {
		return instrutores;
	}

	public void setInstrutores(List<Instrutor> instrutores) {
		this.instrutores = instrutores;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estabelecimento other = (Estabelecimento) obj;
		return Objects.equals(id, other.id);
	}
}
