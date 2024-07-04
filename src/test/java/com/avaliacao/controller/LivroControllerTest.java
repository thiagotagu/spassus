package com.avaliacao.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.avaliacao.entity.Livro;
import com.avaliacao.service.LivroService;

@ExtendWith(MockitoExtension.class)
public class LivroControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LivroService LivroService;

    @InjectMocks
    private LivroCrontroller LivroController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(LivroController).build();
    }


   
    public void testObterId() throws Exception {
        Long id = 1L;
        Livro Livro = new Livro();
        Page<Livro> pageLivros = new PageImpl<>(Collections.singletonList(Livro));
        when(LivroService.findById(id)).thenReturn(Optional.of(Livro));
        when(LivroService.search(any())).thenReturn(pageLivros);

        mockMvc.perform(get("/livro/{pg}/{id}", 0, id))
                .andExpect(status().isOk())
                .andExpect(view().name("cadastro/livro"))
                .andExpect(model().attributeExists("livros"))
                .andExpect(model().attributeExists("livro"));

        verify(LivroService, times(1)).findById(id);
        verify(LivroService, times(1)).search(PageRequest.of(0, 5, Sort.by("id")));
    }

    @Test
    public void testGravarLivros() throws Exception {
        Livro Livro = new Livro();

        mockMvc.perform(post("/livro")
                .flashAttr("livro", Livro))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:livro"));

        verify(LivroService, times(1)).save(Livro);
    }

    @Test
    public void testExcluirLivro() throws Exception {
        Long id = 1L;

        mockMvc.perform(post("/livro/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("redirect:livro"));

        verify(LivroService, times(1)).delete(id);
    }
}
