package com.daniloperez.academia.services;

import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.domain.Atividade;
import com.daniloperez.academia.domain.Instrutor;
import com.daniloperez.academia.domain.ItemScript;
import com.daniloperez.academia.domain.ScriptTreino;
import com.daniloperez.academia.domain.enums.Ativo;
import com.daniloperez.academia.dto.ScriptTreinoDTO;
import com.daniloperez.academia.dto.ScriptTreinoNewDTO;
import com.daniloperez.academia.repositories.AlunoRepository;
import com.daniloperez.academia.repositories.AtividadeRepository;
import com.daniloperez.academia.repositories.InstrutorRepository;
import com.daniloperez.academia.repositories.ItemScriptRepository;
import com.daniloperez.academia.repositories.ScriptTreinoRepository;
import com.daniloperez.academia.security.UserSS;
import com.daniloperez.academia.services.exceptions.AuthorizationException;
import com.daniloperez.academia.services.exceptions.DataIntegrityException;
import com.daniloperez.academia.services.exceptions.ObjectNotFoundException;

@Service
public class ScriptTreinoService {
	@Autowired
	private ScriptTreinoRepository repo;
	
	@Autowired
	private AtividadeRepository atvRepo;
	
	@Autowired
	private ItemScriptRepository itemScriptRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private InstrutorRepository instrutorRepository;
	
	
	@Autowired
	private AlunoService alunoService;
	//Buscar ScriptTreino por ID
		public ScriptTreino find(Integer id) {
			Optional<ScriptTreino> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Treino não encontrado! Id: " + id + ", Tipo: " + ScriptTreino.class.getName()));
		}
		

	//Incluir ScriptTreino
	@Transactional
	public ScriptTreinoDTO insert(ScriptTreinoDTO obj) {
		ScriptTreino script = new ScriptTreino();
		script.setDataCriacao(new Date());
		script.setAtivo(Ativo.INATIVO);
		
		Aluno aluno = alunoRepository.findById(obj.getAluno().getId()).get();
		script.setAluno(aluno);
		
		
		Instrutor instrutor = instrutorRepository.findById(obj.getInstrutor().getId()).get();
		script.setInstrutor(instrutor);
		script = repo.save(script);
		
		System.out.println(obj.getItens().size());
		
		for (ItemScript is : obj.getItens()) {
			Atividade atividade = atvRepo.findById(is.getAtividade().getId()).get();
			is.setAtividade(atividade);
			is.setScriptTreino(script);
			ItemScript itemScript = itemScriptRepository.save(is);
			System.out.println(itemScript);
		}
		
		return obj;
	}
	
	//Excluir ScriptTreino
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	//Alterar ScriptTreino
	public ScriptTreino update(ScriptTreino obj) {
		ScriptTreino newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(ScriptTreino newObj, ScriptTreino obj) {//função auxiliar para atualizar o ScriptTreino e manter dados que não foram modificados.
		newObj.setAtivo(obj.getAtivo());
	}
	
	public ScriptTreino fromDTO(ScriptTreinoNewDTO objDto) {
		 ScriptTreino scr = new ScriptTreino(null, null, null, null, objDto.getAtivo());
		 return scr;
	}
	
	//Buscar ScriptsTreinos somente do aluno que está logado 
	public Page<ScriptTreinoDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		//Verifica se o usuário está autenticado.
		if(user==null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Aluno aluno = alunoService.find(user.getId());
		Page <ScriptTreino> script = repo.findByAluno(aluno, pageRequest);
		
		Page<ScriptTreinoDTO> dtoPage = script.map(new Function<ScriptTreino, ScriptTreinoDTO>() {
		    @Override
		    public ScriptTreinoDTO apply(ScriptTreino scriptTreino) {
		    	ScriptTreinoDTO dto = new ScriptTreinoDTO();
		    	dto.setAluno(scriptTreino.getAluno());
		    	dto.setAtivo(scriptTreino.getAtivo());
		    	dto.setDataCriacao(scriptTreino.getDataCriacao());
		    	dto.setInstrutor(scriptTreino.getInstrutor());
		    	dto.setId(scriptTreino.getId());
		    	dto.setItens(itemScriptRepository.findAllByScriptTreinoOrderByDiasemanaDesc(scriptTreino));

		        return dto;
		    }
		});
		
		return dtoPage;
	}

}
