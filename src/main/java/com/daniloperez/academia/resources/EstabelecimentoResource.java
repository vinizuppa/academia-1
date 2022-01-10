package com.daniloperez.academia.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniloperez.academia.domain.Estabelecimento;
import com.daniloperez.academia.dto.EstabelecimentoDTO;
import com.daniloperez.academia.services.EstabelecimentoService;

@RestController
@RequestMapping(value="/estabelecimentos")
public class EstabelecimentoResource {
	
	@Autowired
	private EstabelecimentoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Estabelecimento> find(@PathVariable Integer id) {
		
		Estabelecimento obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/plano/{id}", method=RequestMethod.GET)
	public ResponseEntity <List <Estabelecimento>> findByPlano(@PathVariable Integer id) {
		
		return ResponseEntity.ok().body(service.findByPlano(id));
	}
	
	//Configurando o metodo POST para Estabelecimento
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EstabelecimentoDTO objDto){ //O @RequestBody faz o JSON ser convertido para objeto java automaticamente.
		Estabelecimento obj = service.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//Chamada que pega a URI do novo recurso que foi add no banco
			return ResponseEntity.created(uri).build();
	}
		
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Estabelecimento obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Estabelecimento>> findAll() {
		
		List<Estabelecimento> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	//Configurando o metodo POST para Envio de Imagens
		@RequestMapping(value="/picture",method=RequestMethod.POST)
		public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file){
			URI uri = service.uploadProfilePicture(file);
			return ResponseEntity.created(uri).build();
		}
		
		//Busca de Estabelecimento por Email
		@RequestMapping(value="/email", method=RequestMethod.GET)
		public ResponseEntity<Estabelecimento> find(@RequestParam(value="value") String email) {
			Estabelecimento obj = service.findByEmail(email);
			return ResponseEntity.ok().body(obj);
		}
}
