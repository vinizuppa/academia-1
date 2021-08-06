package com.daniloperez.academia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.repositories.EnderecoRepository;
import com.daniloperez.academia.repositories.InstrutorRepository;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

//@Service é a anotação para indicar que esse é um service
@Service
public class InstrutorService {
	@Autowired
	private InstrutorRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	//Buscar Instrutor por ID
	public Instrutor find(Integer id) {
		Optional<Instrutor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Instrutor não encontrado! Id: " + id + ", Tipo: " + Instrutor.class.getName()));
	}
	
	//Incluir aluno
	public Instrutor insert(Instrutor obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
}
