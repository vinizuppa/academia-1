package com.daniloperez.academia.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.daniloperez.academia.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Instrutor extends Usuario{
	private static final long serialVersionUID = 1L;
	private String numCrf;
	private String cpf;
	
	@JsonIgnore
	@OneToMany(mappedBy = "instrutor", cascade=CascadeType.ALL)// cascade=CascadeType.ALL serve para indicar que se for apagar o instrutor do banco deve ser apagado as avaliações dele também
	private List<AvaliacaoAluno> avaliacoes = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "instrutor")
	private List<ScriptTreino> scripts = new ArrayList<>();
	
	public Instrutor() {
		
	}
	
	public Instrutor(Integer integer, Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8) {
		
	}
	
	public Instrutor(Integer id,String nome, String email, String cpf, Date data_nasc, Date data_cad, char sexo, String numCrf, String senha) {
		super(id, nome, email, data_nasc, data_cad, sexo, senha);
		this.numCrf = numCrf;
		this.cpf = cpf;
		addPerfil(Perfil.INSTRUTOR);
	}

	public String getNumCrf() {
		return numCrf;
	}

	public void setNumCrf(String numCrf) {
		this.numCrf = numCrf;
	}

	public List<AvaliacaoAluno> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<AvaliacaoAluno> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public List<ScriptTreino> getScripts() {
		return scripts;
	}

	public void setScripts(List<ScriptTreino> scripts) {
		this.scripts = scripts;
	}


	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
