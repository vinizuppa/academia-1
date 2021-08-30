package com.daniloperez.academia.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.dto.InstrutorDTO;
import com.daniloperez.academia.dto.InstrutorNewDTO;
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
		//Declarando objeto para utilizar o metodo buscar da InstrutorService, passando o Id.
		Instrutor obj = service.find(id);
			
		//Retornando que a operação ocorreu com sucesso, retornando o objeto obj que criamos.
		return ResponseEntity.ok().body(obj);
	}
	
	//Busca de Instrutor por Email
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<Instrutor> find(@RequestParam(value="value") String email) {
		Instrutor obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	//Configurando para listar todos Instrutores
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<InstrutorDTO>> findAll() {
		List<Instrutor> list = service.findAll();
		List<InstrutorDTO> listDto = list.stream().map(obj -> new InstrutorDTO(obj)).collect(Collectors.toList()); // Convertendo cada objeto da lista para DTO
		//Retornando que a operação ocorreu com sucesso, retornando o objeto obj que criamos.
		return ResponseEntity.ok().body(listDto);
	}
	
	//Configurando o metodo POST para Instrutor
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody InstrutorNewDTO objDto){ //O @RequestBody faz o JSON ser convertido para objeto java automaticamente.
		Instrutor obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//Chamada que pega a URI do novo recurso que foi add no banco
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	//Configurando o metodo DELETE para Instrutor
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	//Configurando para o ID da URL passar para a váriavel Id
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}	
	
	//Configurando o metodo PUT para Instrutor
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid @RequestBody InstrutorDTO objDto, @PathVariable Integer id){
			Instrutor obj = service.fromDTO(objDto);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
	//Configurando o metodo POST para Envio de Imagens
	@RequestMapping(value="/picture",method=RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file){
		URI uri = service.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
}
