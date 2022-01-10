package com.daniloperez.academia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.Categoria;
import com.daniloperez.academia.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	//Buscar todos Categorias
		public List<Categoria> findAll(){
			return repo.findAllByOrderById();
		}
}
