package com.avaliacao.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avaliacao.entity.Assunto;
import com.avaliacao.entity.Autor;
import com.avaliacao.entity.Livro;
import com.avaliacao.repository.AssuntoRepository;
import com.avaliacao.repository.AutorRepository;
import com.avaliacao.repository.LivroRepository;

@Component
public class CargaMassaTesteFAKE {
	
	
		@Autowired
	    private LivroRepository livroRepository;

	    @Autowired
	    private AutorRepository autorRepository;

	    @Autowired
	    private AssuntoRepository assuntoRepository;

	public void gerarCarga() {
	 
		 Autor autor1 = criarAutor("Stephen King");
		    Autor autor2 = criarAutor("J.K. Rowling");
		    Autor autor3 = criarAutor("George Orwell");
		    Autor autor4 = criarAutor("Agatha Christie");
		    Autor autor5 = criarAutor("Jorge Amado");
		    Autor autor6 = criarAutor("Machado de Assis");

		    Assunto assunto1 = criarAssunto("Ficção Científica");
		    Assunto assunto2 = criarAssunto("Fantasia");
		    Assunto assunto3 = criarAssunto("Horror");
		    Assunto assunto4 = criarAssunto("Romance Policial");
		    Assunto assunto5 = criarAssunto("Literatura Brasileira");
		    Assunto assunto6 = criarAssunto("Clássico");

		    Livro livro1 = criarLivro("A Torre Negra", "Editora A", 1, "2005", autor1, assunto3);
		    Livro livro2 = criarLivro("Harry Potter e a Pedra Filosofal", "Editora B", 1, "1997", autor2, assunto2);
		    Livro livro3 = criarLivro("1984", "Editora C", 1, "1949", autor3, assunto1);
		    Livro livro4 = criarLivro("Assassinato no Expresso Oriente", "Editora D", 1, "1934", autor4, assunto4);
		    Livro livro5 = criarLivro("Capitães da Areia", "Editora E", 1, "1937", autor5, assunto5);
		    Livro livro6 = criarLivro("Dom Casmurro", "Editora F", 1, "1899", autor6, assunto6);
		    livroRepository.saveAll(Arrays.asList(livro1, livro2, livro3, livro4, livro5, livro6 /*, livro7, ... */));
        
		    
		    List<Livro> livros = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            Autor autor = getRandomAutor(Arrays.asList(autor1, autor2, autor3, autor4, autor5));
            Assunto assunto = getRandomAssunto(Arrays.asList(assunto1, assunto2, assunto3, assunto4, assunto5));

            Livro livro = criarLivro("Livro " + i, "Editora " + i, i, "200" + i, autor, assunto);
            livros.add(livro);
        }
        
        livroRepository.saveAll(livros);
  
	}
	
	private Autor getRandomAutor(List<Autor> autores) {
	    Random random = new Random();
	    return autores.get(random.nextInt(autores.size()));
	}

	// Método para obter um assunto aleatório da lista
	private Assunto getRandomAssunto(List<Assunto> assuntos) {
	    Random random = new Random();
	    return assuntos.get(random.nextInt(assuntos.size()));
	}
	
	private Autor criarAutor(String nome) {
        Autor autor = new Autor();
        autor.setNome(nome);
        return autorRepository.save(autor);
    }

	
	 private Assunto criarAssunto(String descricao) {
	        Assunto assunto = new Assunto();
	        assunto.setDescricao(descricao);
	        return assuntoRepository.save(assunto);
	    }

	 private Livro criarLivro(String titulo, String editora, Integer edicao, String anoPublicacao, Autor autor, Assunto assunto) {
	        Livro livro = new Livro();
	        livro.setTitulo(titulo);
	        livro.setEditora(editora);
	        livro.setEdicao(edicao);
	        livro.setAnoPublicacao(anoPublicacao);
	        livro.setAutor(autor);
	        livro.setAssunto(assunto);
	        return livro;
	    }
}
