package com.algaworks.brewer.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.CervejasRepository;

@Service
public class CadastroCervejaService {

	@Autowired
	private CervejasRepository cervejasRepository;
	
	@Transactional
	public void salvar(Cerveja cerveja) {
		cervejasRepository.save(cerveja);
	}
	
}
