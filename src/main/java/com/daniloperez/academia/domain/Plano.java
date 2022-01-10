package com.daniloperez.academia.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plano implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Double valor;
	private String nome;
	private String descricao;
	
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "plano")
//	private List<Matricula> matriculas = new ArrayList<>();
	
	public Plano() {
		
	}

	public Plano(Integer id, Double valor, String nome, String descricao) {
		super();
		this.id = id;
		this.valor = valor;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


//	public List<Matricula> getMatriculas() {
//		return matriculas;
//	}
//
//	public void setMatriculas(List<Matricula> matriculas) {
//		this.matriculas = matriculas;
//	}

	@Override
	public String toString() {
		return "Plano [id=" + id + ", valor=" + valor + ", nome=" + nome + ", descricao=" + descricao + "]";
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
		Plano other = (Plano) obj;
		return Objects.equals(id, other.id);
	}
}
