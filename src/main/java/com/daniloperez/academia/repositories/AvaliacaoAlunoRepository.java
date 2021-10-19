package com.daniloperez.academia.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.domain.AvaliacaoAluno;
import com.daniloperez.academia.dto.AvaliacaoAlunonewDTO;


//Aqui iremos realizar acesso a dados. Pode ser: Buscar, salvar, deletar,
@Repository
public interface AvaliacaoAlunoRepository extends JpaRepository<AvaliacaoAluno, Integer>{
	@Transactional(readOnly=true)
	Page<AvaliacaoAluno> findByAluno(Aluno aluno, Pageable pageRequest);
}
