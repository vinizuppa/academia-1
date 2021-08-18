package com.daniloperez.academia.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniloperez.academia.domain.ItemScript;
import com.daniloperez.academia.domain.ScriptTreino;
import com.daniloperez.academia.domain.enums.Ativo;
import com.daniloperez.academia.repositories.ItemScriptRepository;
import com.daniloperez.academia.repositories.ScriptTreinoRepository;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

@Service
public class ScriptTreinoService {
	@Autowired
	private ScriptTreinoRepository repo;
	
	@Autowired
	private ItemScriptRepository itemScriptRepository;
	//Buscar ScriptTreino por ID
		public ScriptTreino find(Integer id) {
			Optional<ScriptTreino> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Treino não encontrado! Id: " + id + ", Tipo: " + ScriptTreino.class.getName()));
		}
		
	//Incluir aluno
	@Transactional
	public ScriptTreino insert(ScriptTreino obj) {
		obj.setId(null);
		obj.setDataCriacao(new Date());
		obj.setAtivo(Ativo.INATIVO);
		for (ItemScript is : obj.getItens()) {
			is.setScript(obj);
		}
		obj = repo.save(obj);
		itemScriptRepository.saveAll(obj.getItens());
		return obj;
	}	

}
