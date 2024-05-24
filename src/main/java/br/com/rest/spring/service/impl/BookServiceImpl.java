package br.com.rest.spring.service.impl;

import br.com.rest.spring.controller.PersonController;
import br.com.rest.spring.data.vo.v1.BookVO;
import br.com.rest.spring.mapper.DozerMapper;
import br.com.rest.spring.repository.BookRepository;
import br.com.rest.spring.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookVO> findAll() {
        log.info("Find all books");
        List<BookVO> bookVOS = DozerMapper.parseListObjects(bookRepository.findAll(), BookVO.class);
        bookVOS.forEach(bookVO -> bookVO.add(linkTo(methodOn(PersonController.class).findById(bookVO.getBookId())).withSelfRel()));
        return bookVOS;    }
}
