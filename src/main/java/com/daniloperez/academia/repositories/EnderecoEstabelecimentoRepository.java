package com.daniloperez.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniloperez.academia.domain.EnderecoEstabelecimento;



//Aqui iremos realizar acesso a dados. Pode ser: Buscar, salvar, deletar,
@Repository
public interface EnderecoEstabelecimentoRepository extends JpaRepository<EnderecoEstabelecimento, Integer>{

}
