package com.daniloperez.academia.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.domain.ScriptTreino;

@Repository
public interface ScriptTreinoRepository extends JpaRepository<ScriptTreino, Integer>{
	@Transactional(readOnly=true)
	Page<ScriptTreino> findByAluno(Aluno aluno, Pageable pageRequest);
}
