package com.daniloperez.academia.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.AvaliacaoAluno;
import com.daniloperez.academia.repositories.AvaliacaoAlunoRepository;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

@Service
public class AvaliacaoAlunoService {
	@Autowired
	AvaliacaoAlunoRepository repo;
	
	//Buscar AvaliacaoAluno por ID
	public AvaliacaoAluno find(Integer id) {
		Optional<AvaliacaoAluno> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Avaliação não encontrada! Id:" + id + ", Tipo: " + AvaliacaoAluno.class.getName()));
	}
	
	//Incluir Avaliação de Aluno
	public AvaliacaoAluno insert(AvaliacaoAluno obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	//Deletar AvaliacaoAluno
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
}
