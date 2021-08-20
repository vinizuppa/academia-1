package com.daniloperez.academia.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daniloperez.academia.domain.Atividade;
import com.daniloperez.academia.services.AtividadeService;

@RestController
@RequestMapping(value="/atividades")
public class AtividadeResource {
	@Autowired
	private AtividadeService service;
	
	//Configurando End-Point para ser /atividades/id
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	//Configurando para o ID da URL passar para a váriavel Id
	public ResponseEntity<Atividade> find(@PathVariable Integer id) {
		//Declarando objeto para utilizar o metodo buscar da AtividadeService, passando o Id.
		Atividade obj = service.find(id);
		//Retornando que a operação ocorreu com sucesso, retornando o objeto obj que criamos.
		return ResponseEntity.ok().body(obj);
	}
	
	//Listar todas Atividades
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Atividade>> findAll(){
		List<Atividade> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
}
