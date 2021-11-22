package com.daniloperez.academia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.Matricula;
import com.daniloperez.academia.repositories.MatriculaRepository;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

@Service
public class MatriculaService {
	
	@Autowired
	private MatriculaRepository repo;
	
	public Matricula find(Integer id) {
		Optional<Matricula> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Matricula não encontrada! Id: " + id + ", Tipo: " + Matricula.class.getName()));
	}
	
	public Matricula insert(Matricula obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Matricula update(Matricula obj) {
		find(obj.getId());
		return repo.save(obj);
	}
}
