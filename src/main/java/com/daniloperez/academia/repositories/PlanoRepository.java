package com.daniloperez.academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniloperez.academia.domain.Plano;



//Aqui iremos realizar acesso a dados. Pode ser: Buscar, salvar, deletar,
@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer>{
	@Transactional(readOnly=true)
	public List<Plano> findAllByOrderByNome();//Traz todos estados cadastrados, ordenados por nome
}
