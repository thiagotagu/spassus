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

import com.avaliacao.entity.Autor;
import com.avaliacao.service.AutorService;

@ExtendWith(MockitoExtension.class)
public class AutorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AutorService AutorService;

    @InjectMocks
    private AutorCrontroller AutorController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(AutorController).build();
    }


    @Test
    public void testObterId() throws Exception {
        Long id = 1L;
        Autor Autor = new Autor();
        Page<Autor> pageAutors = new PageImpl<>(Collections.singletonList(Autor));
        when(AutorService.findById(id)).thenReturn(Optional.of(Autor));
        when(AutorService.search(any())).thenReturn(pageAutors);

        mockMvc.perform(get("/autor/{pg}/{id}", 0, id))
                .andExpect(status().isOk())
                .andExpect(view().name("cadastro/autor"))
                .andExpect(model().attributeExists("autores"))
                .andExpect(model().attributeExists("autor"));

        verify(AutorService, times(1)).findById(id);
        verify(AutorService, times(1)).search(PageRequest.of(0, 5, Sort.by("id")));
    }

    @Test
    public void testGravarAutors() throws Exception {
        Autor Autor = new Autor();

        mockMvc.perform(post("/autor")
                .flashAttr("autor", Autor))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:autor"));

        verify(AutorService, times(1)).save(Autor);
    }

    @Test
    public void testExcluirAutor() throws Exception {
        Long id = 1L;

        mockMvc.perform(post("/autor/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("redirect:autor"));

        verify(AutorService, times(1)).delete(id);
    }
}
