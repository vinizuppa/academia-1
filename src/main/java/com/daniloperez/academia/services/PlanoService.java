package com.daniloperez.academia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.Plano;
import com.daniloperez.academia.repositories.PlanoRepository;

//@Service é a anotação para indicar que esse é um service
@Service
public class PlanoService {
	@Autowired
	PlanoRepository repo;
	
	
	//Buscar todos Planos
	public List<Plano> findAll(){
		return repo.findAllByOrderById();
	}
	

}
