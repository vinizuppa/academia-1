package com.daniloperez.academia.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.services.InstrutorService;

@RestController
@RequestMapping(value="/instrutores")
public class InstrutorResource {
	
	@Autowired
	private InstrutorService service;
	
	//Configurando para o End-Point ser /instrutores/id
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	//Configurando para o ID da URL passar para a váriavel Id
	public ResponseEntity<Instrutor> find(@PathVariable Integer id) {
		//Declarando objeto para utilizar o metodo buscar da AlunoService, passando o Id.
		Instrutor obj = service.find(id);
			
		//Retornando que a operação ocorreu com sucesso, retornando o objeto obj que criamos.
		return ResponseEntity.ok().body(obj);
	}
	
	//Configurando o metodo POST para Instrutor
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Instrutor obj){ //O @RequestBody faz o JSON ser convertido para objeto java automaticamente.
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//Chamada que pega a URI do novo recurso que foi add no banco
		return ResponseEntity.created(uri).build();
	}
}
