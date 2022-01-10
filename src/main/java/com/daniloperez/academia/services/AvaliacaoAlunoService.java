package com.daniloperez.academia.services;



import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.domain.AvaliacaoAluno;
import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.dto.AvaliacaoAlunoDTO;
import com.daniloperez.academia.dto.AvaliacaoAlunonewDTO;
import com.daniloperez.academia.repositories.AlunoRepository;
import com.daniloperez.academia.repositories.AvaliacaoAlunoRepository;
import com.daniloperez.academia.repositories.InstrutorRepository;
import com.daniloperez.academia.security.UserSS;
import com.daniloperez.academia.services.exceptions.AuthorizationException;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

@Service
public class AvaliacaoAlunoService {
	@Autowired
	AvaliacaoAlunoRepository repo;
	
	@Autowired
	AlunoRepository alunoRepo;
	
	@Autowired
	InstrutorRepository insRepo;
	
	@Autowired
	private AlunoService alunoService;
	
	//Buscar AvaliacaoAluno por ID
	public AvaliacaoAluno find(Integer id) {
		Optional<AvaliacaoAluno> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Avaliação não encontrada! Id:" + id + ", Tipo: " + AvaliacaoAluno.class.getName()));
	}
	
	//Incluir Avaliação de Aluno
	public AvaliacaoAluno insert(AvaliacaoAluno obj) {
		obj.setId(null);
		obj.setData(new Date());
		obj = repo.save(obj);
		return obj;
	}
	
	//Incluir AvaliacaoAluno por DTO
	public AvaliacaoAluno fromDTO(AvaliacaoAlunonewDTO objDto) {
		Optional <Aluno> aluno = alunoRepo.findById(objDto.getAlunoId());
		AvaliacaoAluno av1 = new AvaliacaoAluno(null, objDto.getAltura(), objDto.getPeso(), objDto.getImc(), objDto.getObs(), objDto.getData());
		
		Optional<Instrutor> instrutor = insRepo.findById(objDto.getInstrutorId());
		av1.setInstrutor(instrutor.get());
		av1.setAluno(aluno.get());
		return av1;
	}
	
	
	//Deletar AvaliacaoAluno
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	
	public AvaliacaoAluno fromDTO(AvaliacaoAlunoDTO objDto) {
		 AvaliacaoAluno ava = new AvaliacaoAluno(objDto.getId(), objDto.getAltura(), objDto.getPeso(), objDto.getImc(), objDto.getObs(), null);
		 return ava;
	}
	
	//Buscar AvaliacoesAluno somente do aluno que está logado 
	public Page<AvaliacaoAluno> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		//Verifica se o usuário está autenticado.
		if(user==null) {
			throw new AuthorizationException("Acesso negado");
		}
				
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Aluno aluno = alunoService.find(user.getId());
		return repo.findByAluno(aluno, pageRequest);
	}
}
