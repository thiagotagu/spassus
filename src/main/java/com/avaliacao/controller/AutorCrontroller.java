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

import com.avaliacao.entity.Autor;
import com.avaliacao.service.AutorService;

@Controller
@RequestMapping("autor")
public class AutorCrontroller {
	
	@Autowired
	AutorService autorService;
	
	   @GetMapping
	    public String getAutores(Model model, @PageableDefault(size = 5) Pageable pageable,RedirectAttributes redirectAttributes) {
		   	
		   model.addAttribute("autores", autorService.search(pageable));
		   model.addAttribute("autor", new Autor());
	        return "cadastro/autor";
	    }
	   
	   @GetMapping("{pg}/{id}")
	    public String obterId(Model model , @PathVariable(value = "pg") int pg , @PathVariable(value = "id") Long id,RedirectAttributes redirectAttributes) {
			
			model.addAttribute("autores", autorService.search(PageRequest.of(pg, 5, Sort.by("id"))));
			model.addAttribute("autor", autorService.findById(id));
	        return  "cadastro/autor";
	    }
	   
	   	@PostMapping 
	    public String gravarAutores(Model model, @ModelAttribute Autor autor ,BindingResult result, RedirectAttributes redirectAttributes) {
	   	 if (result.hasErrors()) {
	            model.addAttribute("messageError", "Erro na validação do formulário.");
	            return "cadastro/autor";
	        }

	        try {
	            autorService.save(autor);
	            redirectAttributes.addFlashAttribute("message", "autor " + autor.getNome() + " gravado com Sucesso");
	        } catch (Exception e) {
	            redirectAttributes.addFlashAttribute("messageError", "Erro ao salvar o autor: " + e.getMessage());
	            model.addAttribute("messageError", "Erro ao salvar o autor: " + e.getMessage());
	            return "cadastro/autor";
	        }

	        return "redirect:autor";
			
	    }
	   	
		@PostMapping("{id}")
		@ResponseBody
	    public String excluirAutor(@PathVariable(value = "id") Long id  ) {
				
				autorService.delete(id);
			  return "redirect:autor";
			
	    }

}
