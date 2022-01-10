package com.daniloperez.academia.dto;

import java.io.Serializable;

public class MatriculaDTO implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	private Integer planoId;
	
	public MatriculaDTO() {
		
	}

	public MatriculaDTO(Integer planoId) {
		this.planoId = planoId;
	}

	public Integer getPlanoId() {
		return planoId;
	}

	public void setPlanoId(Integer planoId) {
		this.planoId = planoId;
	}
	
	
}
