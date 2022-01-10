package com.daniloperez.academia.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daniloperez.academia.domain.Plano;
import com.daniloperez.academia.services.PlanoService;

@RestController
@RequestMapping(value="/planos")
public class PlanoResource {
	
	@Autowired
	private PlanoService service;

	//Configurando para listar todos Planos
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<Plano>> findAll(){
			List<Plano> list = service.findAll();
			return ResponseEntity.ok().body(list);
		}
}
