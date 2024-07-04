package com.avaliacao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.avaliacao.entity.Livro;
import com.avaliacao.repository.LivroRepository;


@Service
public class LivroService  extends AbstractService<Livro, Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	LivroRepository dao;

	@Override
	public CrudRepository<Livro, Long> getRepository() {
		return dao;
	}

		public Page<Livro> search(Pageable pageable) {
	        return dao.search(pageable);
	    }

		public void delete(Long id) {
			Optional<Livro> livro = findById(id);
			
			livro.ifPresent(lv ->{
				delete(lv);
			});
		}

 
}