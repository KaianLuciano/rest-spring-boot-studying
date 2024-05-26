package br.com.rest.spring.unittests.mapper.mocks;

import br.com.rest.spring.data.vo.v1.BookVO;
import br.com.rest.spring.model.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockBook {

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> bookVOS = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            bookVOS.add(mockVO(i));
        }
        return bookVOS;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setAuthor("Author Test" + number);
        book.setLaunchDate(LocalDate.now());
        book.setPrice(((number % 2)==0) ? 100.0 : 200.0);
        book.setTitle("Title Test");
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO bookVO = new BookVO();
        bookVO.setAuthor("Author Test" + number);
        bookVO.setLaunchDate(LocalDate.now());
        bookVO.setPrice(((number % 2)==0) ? 100.0 : 200.0);
        bookVO.setTitle("Title Test");
        return bookVO;
    }
}
