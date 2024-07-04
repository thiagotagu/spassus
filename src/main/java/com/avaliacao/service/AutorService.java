package com.avaliacao.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.avaliacao.entity.Autor;
import com.avaliacao.repository.AutorRepository;


@Service
public class AutorService  extends AbstractService<Autor, Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	AutorRepository dao;

	@Override
	public CrudRepository<Autor, Long> getRepository() {
		return dao;
	}

		public Page<Autor> search(Pageable pageable) {
	        return dao.search(pageable);
	    }

		public void delete(Long id) {
			Optional<Autor> autor = findById(id);
			
			autor.ifPresent(lv ->{
				delete(lv);
			});
		}

 
}