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

import com.avaliacao.entity.Livro;
import com.avaliacao.service.AssuntoService;
import com.avaliacao.service.AutorService;
import com.avaliacao.service.LivroService;

@Controller
@RequestMapping("livro")
public class LivroCrontroller {

	@Autowired
	LivroService livroService;

	@Autowired
	AssuntoService assuntoService;

	@Autowired
	AutorService autorService;

	@GetMapping
	public String getLivros(Model model, @PageableDefault(size = 5) Pageable pageable,
			RedirectAttributes redirectAttributes) {

		model.addAttribute("livros", livroService.search(pageable));

		model.addAttribute("lstAutores", autorService.findAll());
		model.addAttribute("lstAssuntos", assuntoService.findAll());
		model.addAttribute("livro", new Livro());
		return "cadastro/livro";
	}

	@GetMapping("{pg}/{id}")
	public String obterId(Model model, @PathVariable(value = "pg") int pg, @PathVariable(value = "id") Long id,
			RedirectAttributes redirectAttributes) {

		model.addAttribute("livros", livroService.search(PageRequest.of(pg, 5, Sort.by("id"))));
		model.addAttribute("lstAutores", autorService.findAll());
		model.addAttribute("lstAssuntos", assuntoService.findAll());
		model.addAttribute("livro", livroService.findById(id));
		return "cadastro/livro";
	}

	@PostMapping
	public String gravarLivros(Model model, @ModelAttribute Livro livro, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("messageError", "Erro na validação do formulário.");
			return "cadastro/livro";
		}

		try {
			livroService.save(livro);
			redirectAttributes.addFlashAttribute("message", "Livro " + livro.getTitulo() + " gravado com Sucesso");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("messageError", "Erro ao salvar o livro: " + e.getMessage());
			model.addAttribute("messageError", "Erro ao salvar o livro: " + e.getMessage());
			return "cadastro/livro";
		}

		return "redirect:livro";

	}

	@PostMapping("{id}")
	@ResponseBody
	public String excluirLivro(@PathVariable(value = "id") Long id) {

		livroService.delete(id);
		return "redirect:livro";

	}

}
