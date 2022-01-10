package com.daniloperez.academia.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.appconfigdata.model.BadRequestException;
import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.domain.Matricula;
import com.daniloperez.academia.domain.Plano;
import com.daniloperez.academia.dto.MatriculaDTO;
import com.daniloperez.academia.repositories.MatriculaRepository;
import com.daniloperez.academia.repositories.PlanoRepository;
import com.daniloperez.academia.security.UserSS;
import com.daniloperez.academia.services.exceptions.AuthorizationException;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

@Service
public class MatriculaService {
	
	@Autowired
	private MatriculaRepository repo;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private PlanoRepository planoRepo;
	
	public Matricula find() {
		UserSS user = UserService.authenticated();
		//Verifica se o usuário está autenticado.
		if(user==null) {
			throw new AuthorizationException("Acesso negado");
		}
	
		Aluno aluno = alunoService.find(user.getId());
		Optional<Matricula> obj = repo.findByAluno(aluno);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Matricula não encontrada! " + ", Tipo: " + Matricula.class.getName()));
	}
	
	public Matricula insert(MatriculaDTO objDTO) {
		UserSS user = UserService.authenticated();
		//Verifica se o usuário está autenticado.
		if(user==null) {
			throw new AuthorizationException("Acesso negado");
		}
		Aluno aluno = alunoService.find(user.getId());
		Plano plano = planoRepo.findById(objDTO.getPlanoId()).get();
		Matricula matricula = new Matricula(new Date(), plano, aluno);

		
		if(repo.existsByAluno(aluno)) {
			throw new BadRequestException("Usuário já tem matricula!!");
		}
		return repo.save(matricula);
	}
	
}
