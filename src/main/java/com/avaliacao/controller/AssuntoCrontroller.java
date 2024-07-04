package com.avaliacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avaliacao.entity.Assunto;
import com.avaliacao.service.AssuntoService;

@Controller
@RequestMapping("assunto")
public class AssuntoCrontroller {
	
	@Autowired
	AssuntoService assuntoService;
	
	   @GetMapping
	    public String getAssuntos(Model model, @PageableDefault(size = 5) Pageable pageable,RedirectAttributes redirectAttributes) {
		   	
		   model.addAttribute("assuntos", assuntoService.search(pageable));
		   model.addAttribute("assunto", new Assunto());
	        return "cadastro/assunto";
	    }
	   
	   @GetMapping("{pg}/{id}")
	    public String obterId(Model model , @PathVariable(value = "pg") int pg , @PathVariable(value = "id") Long id,RedirectAttributes redirectAttributes) {
			
			model.addAttribute("assuntos", assuntoService.search(PageRequest.of(pg, 5, Sort.by("id"))));
			model.addAttribute("assunto", assuntoService.findById(id));
	        return  "cadastro/assunto";
	    }
	   
	   	@PostMapping 
	    public String gravarAssuntos(Model model, @ModelAttribute Assunto assunto ,BindingResult result, RedirectAttributes redirectAttributes) {
	   	 if (result.hasErrors()) {
	            model.addAttribute("messageError", "Erro na validação do formulário.");
	            return "cadastro/assunto";
	        }

	        try {
	            assuntoService.save(assunto);
	            redirectAttributes.addFlashAttribute("message", "assunto " + assunto.getDescricao() + " gravado com Sucesso");
	        } catch (Exception e) {
	            redirectAttributes.addFlashAttribute("messageError", "Erro ao salvar o assunto: " + e.getMessage());
	            model.addAttribute("messageError", "Erro ao salvar o assunto: " + e.getMessage());
	            return "cadastro/assunto";
	        }

	        return "redirect:assunto";
			
	    }
	   	
		@PostMapping("{id}")
		@ResponseBody
	    public String excluirAssunto(@PathVariable(value = "id") Long id  ) {
				
				assuntoService.delete(id);
			  return "redirect:assunto";
			
	    }

}
