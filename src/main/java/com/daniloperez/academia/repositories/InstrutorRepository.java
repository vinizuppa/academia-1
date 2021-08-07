package com.daniloperez.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniloperez.academia.domain.Instrutor;


//Aqui iremos realizar acesso a dados. Pode ser: Buscar, salvar, deletar,
@Repository
public interface InstrutorRepository extends JpaRepository<Instrutor, Integer>{
	//Metodo para buscar no banco de dados se tem algum instrutor com o Email.
	@Transactional(readOnly=true)
	Instrutor findByEmail(String email);
}
