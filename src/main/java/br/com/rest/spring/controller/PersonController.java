package br.com.rest.spring.controller;

import br.com.rest.spring.data.vo.v1.PersonVO;
import br.com.rest.spring.data.vo.v2.PersonVOV2;
import br.com.rest.spring.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping
    public ResponseEntity<List<PersonVO>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonVO> findById(Long personId) {
        return ResponseEntity.ok(personService.findById(personId));
    }

    @PostMapping
    public ResponseEntity<PersonVO> save(PersonVO person) {
        return ResponseEntity.ok(personService.save(person));
    }

    @PostMapping("/v2")
    public ResponseEntity<PersonVOV2> saveV2(PersonVOV2 person) {
        return ResponseEntity.ok(personService.saveV2(person));
    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonVO> update(Long personId, PersonVO person) {
        return ResponseEntity.ok(personService.update(personId, person));
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> delete(Long personId) {
        personService.delete(personId);
        return ResponseEntity.noContent().build();
    }


}
