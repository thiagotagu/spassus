package com.avaliacao.repository;

 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.avaliacao.entity.Assunto;

@Repository
	public interface AssuntoRepository extends JpaRepository<Assunto, Long> {

	
    @Query("FROM Assunto a ")
		Page<Assunto> search(Pageable pageable);




	}
