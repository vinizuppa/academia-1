package com.daniloperez.academia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniloperez.academia.domain.Aluno;
import com.daniloperez.academia.domain.Matricula;

//Aqui iremos realizar acesso a dados. Pode ser: Buscar, salvar, deletar,
@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer>{

	Optional<Matricula> findByAluno(Aluno aluno);

	Boolean existsByAluno(Aluno aluno);
}
