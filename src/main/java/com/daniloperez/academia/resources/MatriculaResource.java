package com.daniloperez.academia.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniloperez.academia.domain.Matricula;
import com.daniloperez.academia.dto.MatriculaDTO;
import com.daniloperez.academia.services.MatriculaService;

@RestController
@RequestMapping(value="/matriculas")
public class MatriculaResource {
	
	@Autowired
	private MatriculaService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Matricula> find() {
		
		Matricula obj = service.find();
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody MatriculaDTO objDTO) {
		service.insert(objDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("").buildAndExpand().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
