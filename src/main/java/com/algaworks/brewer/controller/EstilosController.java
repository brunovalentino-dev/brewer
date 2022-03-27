package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.service.CadastroEstiloService;
import com.algaworks.brewer.service.exception.EstiloCadastradoException;

@Controller
public class EstilosController {

	private static final String ESTILO_VIEW = "estilo/CadastroEstilo";
		
	@Autowired
	private CadastroEstiloService cadastroEstiloService;
		
	@RequestMapping("/estilos/novo")
	public ModelAndView novo(Estilo estilo) {			
		return new ModelAndView(ESTILO_VIEW);
	}
	
	@RequestMapping(value = "/estilos/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult bindingResult, 
			Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return this.novo(estilo);
		}
							
		try {
			cadastroEstiloService.salvar(estilo);
		} 
		catch (EstiloCadastradoException e) {
			bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());			
			
			return this.novo(estilo);
		}
		
		redirectAttributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso!");		
		
		return new ModelAndView("redirect:/estilos/novo");
	}
		
}