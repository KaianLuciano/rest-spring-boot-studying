package br.com.rest.spring.unit.tests.mockito.services;

import br.com.rest.spring.model.Book;
import br.com.rest.spring.repository.BookRepository;
import br.com.rest.spring.service.impl.BookServiceImpl;
import br.com.rest.spring.unit.tests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServicesTest {

    MockBook input;
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @BeforeEach
    void setUpMocks() {
        input = new MockBook();
    }

    @Test
    void testFindById() {
        Book entity = input.mockEntity(1);
        entity.setBookId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = bookServiceImpl.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getBookId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1", result.getAuthor());
        assertEquals(200.0, result.getPrice());
        assertEquals("Title Test", result.getTitle());
    }
}
