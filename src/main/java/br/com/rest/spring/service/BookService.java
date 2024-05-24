package br.com.rest.spring.service;

import br.com.rest.spring.data.vo.v1.BookVO;

import java.util.List;

public interface BookService {
    List<BookVO> findAll();
}
