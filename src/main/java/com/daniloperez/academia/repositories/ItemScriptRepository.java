package com.daniloperez.academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniloperez.academia.domain.ItemScript;
import com.daniloperez.academia.domain.ScriptTreino;

@Repository
public interface ItemScriptRepository extends JpaRepository<ItemScript, Integer>{

	List<ItemScript> findAllByScriptTreino(ScriptTreino scriptTreino);

	List<ItemScript> findAllByScriptTreinoOrderByDiasemanaDesc(ScriptTreino scriptTreino);

}
