package com.algaworks.brewer.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.repository.EstilosRepository;
import com.algaworks.brewer.service.exception.EstiloCadastradoException;

@Service
public class CadastroEstiloService {

	@Autowired
	private EstilosRepository estilosRepository;
	
	@Transactional
	public Estilo salvar(Estilo estilo) {
		Optional<Estilo> optionalEstilo = estilosRepository.findByNomeIgnoreCase(estilo.getNome());
						
		if (optionalEstilo.isPresent()) {
			throw new EstiloCadastradoException("Estilo já cadastrado na base de dados!");
		}		
		
		return estilosRepository.saveAndFlush(estilo);
	}
	
}
