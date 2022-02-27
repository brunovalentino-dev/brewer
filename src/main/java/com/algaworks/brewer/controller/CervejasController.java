package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.CervejasRepository;

@Controller
public class CervejasController {

//	private static final Logger LOG = LoggerFactory.getLogger(CervejasController.class); 
	
	@Autowired
	private CervejasRepository cervejasRepository;
	
	@RequestMapping("/cervejas/novo")
	public String novo(Cerveja cerveja) {		
		cervejasRepository.findAll();
		
		return "cerveja/CadastroCerveja"; 
	}
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult bindingResult, 
			Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return this.novo(cerveja);
		}
				
		System.out.println(">>> SKU: " + cerveja.getSku());
		System.out.println(">>> Nome: " + cerveja.getNome());
		System.out.println(">>> Descrição: " + cerveja.getDescricao());
		
		redirectAttributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");		
		
		return "redirect:/cervejas/novo";
	}
		
}