package br.com.rest.spring.controller;

import br.com.rest.spring.data.vo.v1.PersonVO;
import br.com.rest.spring.data.vo.v2.PersonVOV2;
import br.com.rest.spring.service.PersonService;
import br.com.rest.spring.service.impl.PersonServiceImpl;
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
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Management People")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonServiceImpl personServiceImpl) {
        this.personService = personServiceImpl;
    }


    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    @Operation(summary = "Find all people recorded in the database", description = "Find all people recorded in the database"
            , tags = { "People" }, responses = {
            @ApiResponse(responseCode = "200", description = "People found", content = {
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
    public ResponseEntity<List<PersonVO>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping(value = "/{personId}",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    @Operation(summary = "Finds a Person recorded in the database", description = "Finds a Person recorded in the database"
            , tags = { "People" }, responses = {
            @ApiResponse(responseCode = "200", description = "People found",
                    content = @Content(schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error" , content = @Content)
    })
    public ResponseEntity<PersonVO> findById(@PathVariable Long personId) {
        return ResponseEntity.ok(personService.findById(personId));
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    @Operation(summary = "Save a new Person",
            description = "Save a new Person by passing the JSON, XML or YAML object in the request body."
            , tags = { "People" }, responses = {
            @ApiResponse(responseCode = "200", description = "People found",
                    content = @Content(schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error" , content = @Content)
    })
    public ResponseEntity<PersonVO> save(@RequestBody @NotNull PersonVO person) {
        return ResponseEntity.ok(personService.save(person));
    }

    @PostMapping(value = "/v2",
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    public ResponseEntity<PersonVOV2> saveV2(@RequestBody PersonVOV2 person) {
        return ResponseEntity.ok(personService.saveV2(person));
    }

    @PutMapping(value = "/{personId}",
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    public ResponseEntity<PersonVO> update(@PathVariable Long personId, @RequestBody PersonVO person) {
        return ResponseEntity.ok(personService.update(personId, person));
    }

    @DeleteMapping(value = "/{personId}",
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    public ResponseEntity<Void> delete(@PathVariable Long personId) {
        personService.delete(personId);
        return ResponseEntity.noContent().build();
    }

}
