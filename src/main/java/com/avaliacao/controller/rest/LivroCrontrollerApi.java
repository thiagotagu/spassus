package com.avaliacao.controller.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avaliacao.entity.Livro;
import com.avaliacao.service.LivroService;

@RestController
@RequestMapping("api/livro")
public class LivroCrontrollerApi {

	@Autowired
	LivroService livroService;

	@GetMapping
	public ResponseEntity<Page<Livro>> lstAll(@PageableDefault(size = 5) Pageable pageable) {

		return ResponseEntity.ok().body(livroService.search(pageable));
	}

	@PostMapping
	public ResponseEntity<Livro> add(@RequestBody @Valid Livro cliente) {

		Livro retorno = livroService.save(cliente);
		return ResponseEntity.ok().body(retorno);
	}

	@GetMapping("{pg}/{id}")
	public ResponseEntity<Page<Livro>> obterId(Model model, @PathVariable(value = "pg") int pg,
			@PathVariable(value = "id") Long id) {

		return ResponseEntity.ok().body(livroService.search(PageRequest.of(pg, 5, Sort.by("id"))));

	}

	@GetMapping("{id}")
	public ResponseEntity<Livro> obterId(@PathVariable(value = "id") Long id) {

		Optional<Livro> livro = livroService.findById(id);

		if (livro.isPresent()) {
			return ResponseEntity.ok().body(livro.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
		livroService.delete(id);

		return ResponseEntity.ok().body("Removido com sucesso!");

	}

}
