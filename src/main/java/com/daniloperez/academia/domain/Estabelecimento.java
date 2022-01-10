package com.daniloperez.academia.domain;


import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.daniloperez.academia.domain.enums.Perfil;
@Entity
public class Estabelecimento extends Usuario {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String razao_social;
	private String cnpj;
	
	@ManyToOne
	@JoinColumn(name="plano_id")
	private Plano plano;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	public Estabelecimento() {
		addPerfil(Perfil.ESTABELECIMENTO);
	}

	public Estabelecimento(Integer id, String nome, String email, String razao_social, String cnpj, Date data_nasc,
			Date data_cad, char sexo, String senha) {
		super(id, nome, email, data_nasc, data_cad, sexo, senha);
		this.razao_social = razao_social;
		this.cnpj = cnpj;
		addPerfil(Perfil.ESTABELECIMENTO);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
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
