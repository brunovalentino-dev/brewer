package com.algaworks.brewer.controller;

import java.util.Collections;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Origem;
import com.algaworks.brewer.model.Sabor;
import com.algaworks.brewer.repository.EstilosRepository;
import com.algaworks.brewer.service.CadastroCervejaService;

@Controller
public class CervejasController {

	private static final String CERVEJA_VIEW = "cerveja/CadastroCerveja";
	
	@Autowired
	private EstilosRepository estilosRepository;
	
	@Autowired
	private CadastroCervejaService cadastroCervejaService;
	
	@RequestMapping("/cervejas/novo")
	public ModelAndView novo(Cerveja cerveja) {				
		ModelAndView cervejaModelView = new ModelAndView(CERVEJA_VIEW);
		cervejaModelView.addObject("sabores", Sabor.values());
		cervejaModelView.addObject("estilos", Optional.of(estilosRepository.findAll()).orElse(Collections.emptyList()));
		cervejaModelView.addObject("origens", Origem.values());
		
		return cervejaModelView;
	}
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult bindingResult, 
			Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return this.novo(cerveja);
		}
							
		cadastroCervejaService.salvar(cerveja);
		
		redirectAttributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");		
		
		return new ModelAndView("redirect:/cervejas/novo");
	}
		
}