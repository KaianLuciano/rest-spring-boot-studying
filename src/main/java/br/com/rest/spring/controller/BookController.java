package br.com.rest.spring.controller;

import br.com.rest.spring.data.vo.v1.BookVO;
import br.com.rest.spring.data.vo.v1.PersonVO;
import br.com.rest.spring.service.BookService;
import br.com.rest.spring.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books/v1")
@Tag(name = "Book", description = "Endpoints for Management Books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    @Operation(summary = "Find all books recorded in the database", description = "Find all books recorded in the database"
            , tags = { "Book" }
            , responses = {
            @ApiResponse(responseCode = "200", description = "Book found", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))),
                    @Content(mediaType = MediaType.APPLICATION_XML,
                            array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))),
                    @Content(mediaType = MediaType.APPLICATION_YAML,
                            array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error" , content = @Content)
    })
    public ResponseEntity<List<BookVO>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }
}
