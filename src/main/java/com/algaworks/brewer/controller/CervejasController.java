package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.algaworks.brewer.model.Cerveja;

@Controller
public class CervejasController {

	@RequestMapping("/cervejas/novo")
	public String novo() {
		return "cerveja/CadastroCerveja";
	}
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println(">>> Erro detectado!");
		}
				
		System.out.println(">>> SKU: " + cerveja.getSku());
		System.out.println(">>> Nome: " + cerveja.getNome());
		
		return "cerveja/CadastroCerveja";
	}
	
}