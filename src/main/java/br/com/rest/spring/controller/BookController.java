package br.com.rest.spring.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books/v1")
@Tag(name = "Book", description = "Endpoints for Management Books")
public class BookController {
}
