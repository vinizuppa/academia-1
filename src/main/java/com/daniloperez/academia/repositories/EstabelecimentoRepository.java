package com.daniloperez.academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniloperez.academia.domain.Estabelecimento;
import com.daniloperez.academia.domain.Plano;


//Aqui iremos realizar acesso a dados. Pode ser: Buscar, salvar, deletar,
@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer>{
	//Metodo para buscar no banco de dados se tem algum estabelecimento com o Email.
	@Transactional(readOnly=true)
	Estabelecimento findByEmail(String email);

	List<Estabelecimento> findByPlano(Plano plano);
}
