package br.com.rest.spring.controller;

import br.com.rest.spring.model.Person;
import br.com.rest.spring.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> findById(Long personId) {
        return ResponseEntity.ok(personService.findById(personId));
    }

    @PostMapping
    public ResponseEntity<Person> save(Person person) {
        return ResponseEntity.ok(personService.save(person));
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Person> update(Long personId, Person person) {
        return ResponseEntity.ok(personService.update(personId, person));
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> delete(Long personId) {
        personService.delete(personId);
        return ResponseEntity.noContent().build();
    }


}
