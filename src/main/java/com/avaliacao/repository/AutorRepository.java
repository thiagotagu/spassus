package com.avaliacao.repository;

 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.avaliacao.entity.Autor;

@Repository
	public interface AutorRepository extends JpaRepository<Autor, Long> {

	
    @Query("FROM Autor a ")
		Page<Autor> search(Pageable pageable);




	}
