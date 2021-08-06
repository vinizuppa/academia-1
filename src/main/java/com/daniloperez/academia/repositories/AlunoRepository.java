package com.daniloperez.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniloperez.academia.domain.Aluno;


//Aqui iremos realizar acesso a dados. Pode ser: Buscar, salvar, deletar,
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

}
