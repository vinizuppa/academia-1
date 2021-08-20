package com.daniloperez.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniloperez.academia.domain.ScriptTreino;

@Repository
public interface ScriptTreinoRepository extends JpaRepository<ScriptTreino, Integer>{

}
