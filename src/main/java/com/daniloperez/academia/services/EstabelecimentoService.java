package com.daniloperez.academia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.Estabelecimento;
import com.daniloperez.academia.repositories.EstabelecimentoRepository;
import com.daniloperez.academia.services.exceptions.DataIntegrityException;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

@Service
public class EstabelecimentoService {
	
	@Autowired
	private EstabelecimentoRepository repo;
	
	public Estabelecimento find(Integer id) {
		Optional<Estabelecimento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Estabelecimento não encontrado! Id: " + id + ", Tipo: " + Estabelecimento.class.getName()));
	}
	
	public Estabelecimento insert(Estabelecimento obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Estabelecimento update(Estabelecimento obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível um estabelecimento que possui instrutores!");
		}		
	}
	
	public List<Estabelecimento> findAll() {
		return repo.findAll();
	}
}
