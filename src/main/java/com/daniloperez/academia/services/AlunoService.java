package com.daniloperez.academia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.repositories.AlunoRepository;
import com.daniloperez.academia.repositories.EnderecoRepository;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

//@Service é a anotação para indicar que esse é um service
@Service
public class AlunoService {
	@Autowired
	private AlunoRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	//Buscar Aluno por ID
	public Aluno find(Integer id) {
		Optional<Aluno> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()));
	}
	
	//Incluir aluno
	public Aluno insert(Aluno obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
}
