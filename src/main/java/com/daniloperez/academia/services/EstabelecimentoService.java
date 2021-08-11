package com.daniloperez.academia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.Categoria;
import com.daniloperez.academia.domain.Estabelecimento;
import com.daniloperez.academia.repositories.EstabelecimentoRepository;
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
}
