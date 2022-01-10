package com.daniloperez.academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniloperez.academia.domain.Categoria;


//Aqui iremos realizar acesso a dados. Pode ser: Buscar, salvar, deletar,
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	@Transactional(readOnly=true)
	public List<Categoria> findAllByOrderById();//Traz todos estados cadastrados, ordenados por nome
}
