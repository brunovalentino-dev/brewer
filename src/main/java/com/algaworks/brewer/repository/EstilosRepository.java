package com.algaworks.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Estilo;

@Repository
public interface EstilosRepository extends JpaRepository<Estilo, Long> {

	Optional<Estilo> findByNomeIgnoreCase(String nome);
	
}
