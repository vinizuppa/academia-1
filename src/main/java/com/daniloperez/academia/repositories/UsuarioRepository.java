package com.daniloperez.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniloperez.academia.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	//Metodo para buscar no banco de dados se tem algum usuario com o Email.
		@Transactional(readOnly=true) 
		Usuario findByEmail(String email);
}
