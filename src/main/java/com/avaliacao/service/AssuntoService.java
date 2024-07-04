package com.avaliacao.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.avaliacao.entity.Assunto;
import com.avaliacao.repository.AssuntoRepository;


@Service
public class AssuntoService  extends AbstractService<Assunto, Long> {
 
	private static final long serialVersionUID = 1L;
	
	@Autowired
	AssuntoRepository dao;

	@Override
	public CrudRepository<Assunto, Long> getRepository() {
		return dao;
	}

		public Page<Assunto> search(Pageable pageable) {
	        return dao.search(pageable);
	    }

		public void delete(Long id) {
			Optional<Assunto> Assunto = findById(id);
			
			Assunto.ifPresent(lv ->{
				delete(lv);
			});
		}
}