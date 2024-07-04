package com.avaliacao.service;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.avaliacao.entity.Livro;
import com.avaliacao.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @Mock
    private LivroRepository dao;

    @InjectMocks
    private LivroService service;

    @BeforeEach
    void setUp() {
        service = new LivroService();
        service.dao = dao;
    }
 

    @Test
    void testSearch() {
        Pageable pageable = mock(Pageable.class);
        Page<Livro> page = mock(Page.class);

        when(dao.search(any(Pageable.class))).thenReturn(page);

        Page<Livro> result = service.search(pageable);

        verify(dao, times(1)).search(pageable);
        assertSame(page, result);
    }

    @Test
    void testDelete() {
        Livro Livro = new Livro();
        when(dao.findById(anyLong())).thenReturn(Optional.of(Livro));
        doNothing().when(dao).delete(any(Livro.class));

        service.delete(1L);

        verify(dao, times(1)).findById(1L);
        verify(dao, times(1)).delete(Livro);
    }
}

