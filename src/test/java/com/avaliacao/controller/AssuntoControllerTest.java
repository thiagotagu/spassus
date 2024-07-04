package com.avaliacao.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avaliacao.entity.Assunto;
import com.avaliacao.service.AssuntoService;

@ExtendWith(MockitoExtension.class)
public class AssuntoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AssuntoService assuntoService;

    @InjectMocks
    private AssuntoCrontroller assuntoController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(assuntoController).build();
    }


    @Test
    public void testObterId() throws Exception {
        Long id = 1L;
        Assunto assunto = new Assunto();
        Page<Assunto> pageAssuntos = new PageImpl<>(Collections.singletonList(assunto));
        when(assuntoService.findById(id)).thenReturn(Optional.of(assunto));
        when(assuntoService.search(any())).thenReturn(pageAssuntos);

        mockMvc.perform(get("/assunto/{pg}/{id}", 0, id))
                .andExpect(status().isOk())
                .andExpect(view().name("cadastro/assunto"))
                .andExpect(model().attributeExists("assuntos"))
                .andExpect(model().attributeExists("assunto"));

        verify(assuntoService, times(1)).findById(id);
        verify(assuntoService, times(1)).search(PageRequest.of(0, 5, Sort.by("id")));
    }

    @Test
    public void testGravarAssuntos() throws Exception {
        Assunto assunto = new Assunto();
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        mockMvc.perform(post("/assunto")
                .flashAttr("assunto", assunto))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:assunto"));

        verify(assuntoService, times(1)).save(assunto);
    }

    @Test
    public void testExcluirAssunto() throws Exception {
        Long id = 1L;

        mockMvc.perform(post("/assunto/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("redirect:assunto"));

        verify(assuntoService, times(1)).delete(id);
    }
}
