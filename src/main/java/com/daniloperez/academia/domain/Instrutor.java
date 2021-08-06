package com.daniloperez.academia.domain;
import java.util.Date;
import javax.persistence.Entity;


@Entity
public class Instrutor extends Pessoa{
	private static final long serialVersionUID = 1L;
	private String NumCrf;
	
	public Instrutor() {
	}
	
	public Instrutor(Integer id,String nome, String email, String cpf, Date data_nasc, Date data_cad, char sexo, String NumCrf) {
		super(id, nome, email, cpf, data_nasc, data_cad, sexo);
		this.setNumCrf(NumCrf);
	}

	public String getNumCrf() {
		return NumCrf;
	}

	public void setNumCrf(String numCrf) {
		NumCrf = numCrf;
	}
}
