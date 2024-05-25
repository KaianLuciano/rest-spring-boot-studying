package br.com.rest.spring.controller;

import br.com.rest.spring.data.vo.v1.BookVO;
import br.com.rest.spring.service.BookService;
import br.com.rest.spring.util.MediaType;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                            array = @ArraySchema(schema = @Schema(implementation = BookVO.class))),
                    @Content(mediaType = MediaType.APPLICATION_XML,
                            array = @ArraySchema(schema = @Schema(implementation = BookVO.class))),
                    @Content(mediaType = MediaType.APPLICATION_YAML,
                            array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error" , content = @Content)
    })
    public ResponseEntity<List<BookVO>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping(value = "/{bookId}",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    @Operation(summary = "Finds a Book recorded in the database", description = "Finds a Book recorded in the database"
            , tags = { "People" }
            , responses = {
            @ApiResponse(responseCode = "200", description = "Book found",
                    content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error" , content = @Content)
    })
    public ResponseEntity<BookVO> findById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.findById(bookId));
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    @Operation(summary = "Save a new Book",
            description = "Save a new Book by passing the JSON, XML or YAML object in the request body."
            , tags = { "Book" }
            , responses = {
            @ApiResponse(responseCode = "200", description = "Book Saved with success",
                    content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error" , content = @Content)
    })
    public ResponseEntity<BookVO> save(@RequestBody @NotNull BookVO bookVO) {
        return ResponseEntity.ok(bookService.save(bookVO));
    }



}
