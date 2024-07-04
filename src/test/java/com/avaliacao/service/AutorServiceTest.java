package com.avaliacao.service;
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

import com.avaliacao.entity.Autor;
import com.avaliacao.repository.AutorRepository;

@ExtendWith(MockitoExtension.class)
public class AutorServiceTest {

    @Mock
    private AutorRepository dao;

    @InjectMocks
    private AutorService service;

    @BeforeEach
    void setUp() {
        service = new AutorService();
        service.dao = dao;
    }


    @Test
    void testSearch() {
        Pageable pageable = mock(Pageable.class);
        Page<Autor> page = mock(Page.class);

        when(dao.search(any(Pageable.class))).thenReturn(page);

        Page<Autor> result = service.search(pageable);

        verify(dao, times(1)).search(pageable);
        assertSame(page, result);
    }

    @Test
    void testDelete() {
        Autor autor = new Autor();
        when(dao.findById(anyLong())).thenReturn(Optional.of(autor));
        doNothing().when(dao).delete(any(Autor.class));

        service.delete(1L);

        verify(dao, times(1)).findById(1L);
        verify(dao, times(1)).delete(autor);
    }
}
