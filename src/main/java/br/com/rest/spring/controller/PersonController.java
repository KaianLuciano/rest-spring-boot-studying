package br.com.rest.spring.controller;

import br.com.rest.spring.data.vo.v1.PersonVO;
import br.com.rest.spring.data.vo.v2.PersonVOV2;
import br.com.rest.spring.service.PersonService;
import br.com.rest.spring.service.impl.PersonServiceImpl;
import br.com.rest.spring.util.MediaType;
import com.sun.istack.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonServiceImpl personServiceImpl) {
        this.personService = personServiceImpl;
    }


    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    public ResponseEntity<List<PersonVO>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping(value = "/{personId}",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    public ResponseEntity<PersonVO> findById(@PathVariable Long personId) {
        return ResponseEntity.ok(personService.findById(personId));
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
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
