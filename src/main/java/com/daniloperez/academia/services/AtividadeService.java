package com.daniloperez.academia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.Atividade;
import com.daniloperez.academia.repositories.AtividadeRepository;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

@Service
public class AtividadeService {
	@Autowired
	private AtividadeRepository repo;
	
	//Buscar Atividade por ID
	public Atividade find(Integer id) {
		Optional<Atividade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Atividade não encontrada! Id: " + id + ", Tipo: " + Atividade.class.getName()));
	}
	
	//Buscar todas Atividades
	public List<Atividade> findAll(){
		return repo.findAll();
	}
}
