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

import com.avaliacao.entity.Assunto;
import com.avaliacao.service.AssuntoService;

@RestController
@RequestMapping("api/assunto")
public class AssuntoCrontrollerApi {

	@Autowired
	AssuntoService service;

	@GetMapping
	public ResponseEntity<Page<Assunto>> lstAll(@PageableDefault(size = 5) Pageable pageable) {

		return ResponseEntity.ok().body(service.search(pageable));
	}

	@PostMapping
	public ResponseEntity<Assunto> add(@RequestBody @Valid Assunto cliente) {

		Assunto retorno = service.save(cliente);
		return ResponseEntity.ok().body(retorno);
	}

	@GetMapping("{pg}/{id}")
	public ResponseEntity<Page<Assunto>> obterId(Model model, @PathVariable(value = "pg") int pg,
			@PathVariable(value = "id") Long id) {

		return ResponseEntity.ok().body(service.search(PageRequest.of(pg, 5, Sort.by("id"))));

	}

	@GetMapping("{id}")
	public ResponseEntity<Assunto> obterId(@PathVariable(value = "id") Long id) {

		Optional<Assunto> Assunto = service.findById(id);

		if (Assunto.isPresent()) {
			return ResponseEntity.ok().body(Assunto.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);

		return ResponseEntity.ok().body("Removido com sucesso!");

	}

}
