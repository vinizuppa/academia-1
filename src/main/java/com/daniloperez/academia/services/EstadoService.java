package com.daniloperez.academia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.Estado;
import com.daniloperez.academia.repositories.EstadoRepository;

@Service
public class EstadoService {
	@Autowired
	private EstadoRepository repo;
	
	//Traz todos estados ordenados por nome
	public List<Estado> findAll(){
		return repo.findAllByOrderByNome();
	}
}
