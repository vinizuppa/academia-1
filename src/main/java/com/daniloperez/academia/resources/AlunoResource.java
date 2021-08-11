package com.daniloperez.academia.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.dto.AlunoDTO;
import com.daniloperez.academia.dto.AlunoNewDTO;
import com.daniloperez.academia.services.AlunoService;

@RestController
@RequestMapping(value="/alunos")
public class AlunoResource {
	
	@Autowired
	private AlunoService service;
	
	//Configurando para o End-Point ser /aluno/id
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	//Configurando para o ID da URL passar para a váriavel Id
	public ResponseEntity<Aluno> find(@PathVariable Integer id) {
		//Declarando objeto para utilizar o metodo buscar da AlunoService, passando o Id.
		Aluno obj = service.find(id);
		//Retornando que a operação ocorreu com sucesso, retornando o objeto obj que criamos.
		return ResponseEntity.ok().body(obj);
	}
	
	//Configurando para listar todos Alunos
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AlunoDTO>> findAll() {
		List<Aluno> list = service.findAll();
		List<AlunoDTO> listDto = list.stream().map(obj -> new AlunoDTO(obj)).collect(Collectors.toList()); // Convertendo cada objeto da lista para DTO
		//Retornando que a operação ocorreu com sucesso, retornando o objeto obj que criamos.
		return ResponseEntity.ok().body(listDto);
	}
	
	//Configurando o metodo POST para Aluno
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody AlunoNewDTO objDto){ //O @RequestBody faz o JSON ser convertido para objeto java automaticamente.
		Aluno obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//Chamada que pega a URI do novo recurso que foi add no banco
		return ResponseEntity.created(uri).build();
	}
	
	//Configurando o metodo DELETE para aluno
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	//Configurando para o ID da URL passar para a váriavel Id
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}	
	
	//Configurando o metodo PUT para Aluno
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody AlunoDTO objDto, @PathVariable Integer id){
		Aluno obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
}
