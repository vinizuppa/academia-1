package com.daniloperez.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniloperez.academia.domain.ItemScript;

@Repository
public interface ItemScriptRepository extends JpaRepository<ItemScript, Integer>{

}
