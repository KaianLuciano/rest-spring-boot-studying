package br.com.rest.spring.service.impl;

import br.com.rest.spring.controller.BookController;
import br.com.rest.spring.data.vo.v1.BookVO;
import br.com.rest.spring.exception.handler.exception.Exceptions;
import br.com.rest.spring.mapper.DozerMapper;
import br.com.rest.spring.model.Book;
import br.com.rest.spring.repository.BookRepository;
import br.com.rest.spring.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        bookVOS.forEach(bookVO -> bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getBookId())).withSelfRel()));
        return bookVOS;
    }

    @Override
    public BookVO findById(Long bookId) {
        log.info("Find book");
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new Exceptions.ResourceNotFoundException("Book not found"));
        BookVO bookVO = DozerMapper.parseObject(book, BookVO.class);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getBookId())).withSelfRel());
        return bookVO;
    }

    @Override
    @Transactional
    public BookVO save(BookVO book) {
        if(book == null)
            throw new Exceptions.RequiredObjectIsNullException();
        log.info("Save book");
        Book bookEntity = DozerMapper.parseObject(book, Book.class);
        BookVO bookVO =  DozerMapper.parseObject(bookRepository.save(bookEntity), BookVO.class);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getBookId())).withSelfRel());
        return bookVO;
    }

    @Override
    @Transactional
    public BookVO update(Long bookId, BookVO book) {
        if(book == null)
            throw new Exceptions.RequiredObjectIsNullException();
        log.info("Update book");
        BookVO bookToUpdate = DozerMapper.parseObject(bookRepository.findById(bookId)
                .orElseThrow(() -> new Exceptions.ResourceNotFoundException("Book not found")), BookVO.class);
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setLaunchDate(book.getLaunchDate());
        bookToUpdate.setPrice(book.getPrice());
        bookToUpdate.setTitle(book.getTitle());
        BookVO bookVO =  DozerMapper.parseObject(bookRepository.save(DozerMapper.parseObject(book, Book.class)), BookVO.class);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getBookId())).withSelfRel());
        return bookVO;
    }

    @Override
    @Transactional
    public void delete(Long bookId) {
        log.info("Delete book");
        BookVO bookToUpdate = DozerMapper.parseObject(bookRepository.findById(bookId)
                .orElseThrow(() -> new Exceptions.ResourceNotFoundException("Book not found")), BookVO.class);
        bookRepository.delete(DozerMapper.parseObject(bookToUpdate, Book.class));
    }
}
