package com.avaliacao.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
@Entity
public class Autor {
	
		@Id
	 	@GeneratedValue
		Long id;
		
		@Size( max = 40)
		String nome;
		

}
