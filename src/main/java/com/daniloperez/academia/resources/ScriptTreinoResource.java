package com.daniloperez.academia.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniloperez.academia.domain.ScriptTreino;
import com.daniloperez.academia.dto.ScriptTreinoDTO;
import com.daniloperez.academia.dto.ScriptTreinoNewDTO;
import com.daniloperez.academia.services.ScriptTreinoService;

@RestController
@RequestMapping(value="/treinos")
public class ScriptTreinoResource {
	@Autowired
	private ScriptTreinoService service;
	
	//Configurando para o End-Point ser /treinos/id
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	//Configurando para o ID da URL passar para a váriavel Id
	public ResponseEntity<ScriptTreino> find(@PathVariable Integer id) {
		//Declarando objeto para utilizar o metodo buscar da ScriptTreinoService, passando o Id.
		ScriptTreino obj = service.find(id);
		//Retornando que a operação ocorreu com sucesso, retornando o objeto obj que criamos.
		return ResponseEntity.ok().body(obj);
	}
	
	//Configurando o metodo POST para ScriptTreino
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ScriptTreinoDTO obj){ //O @RequestBody faz o JSON ser convertido para objeto java automaticamente.
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//Chamada que pega a URI do novo recurso que foi add no banco
		return ResponseEntity.created(uri).build();
	}
	
	//Configurando o metodo DELETE para ScriptTreino
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	//Configurando para o ID da URL passar para a váriavel Id
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}	
	
	//Configurando o metodo PUT para ScriptTreino
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ScriptTreinoNewDTO objDto, @PathVariable Integer id){
		ScriptTreino obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	//Buscando ScriptTreinos somente do aluno logado.
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ScriptTreinoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dataCriacao") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<ScriptTreinoDTO> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}
