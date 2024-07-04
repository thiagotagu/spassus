package com.avaliacao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Livro {

	@Id
	@GeneratedValue
	Long id;

	@Size(max = 40)
	String titulo;

	@Size(max = 40)
	String editora;

	Integer edicao;

	String AnoPublicacao;

	@ManyToOne
	@JoinColumn(name = "id_autor")
	Autor autor;

	@ManyToOne
	@JoinColumn(name = "id_assunto")
	Assunto assunto;

}
