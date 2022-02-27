package com.algaworks.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Cerveja;

@Repository
public interface CervejasRepository extends JpaRepository<Cerveja, Long>{

}
