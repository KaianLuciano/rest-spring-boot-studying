package br.com.rest.spring.controller;

import br.com.rest.spring.model.Person;
import br.com.rest.spring.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> findById(Long personId) {
        return ResponseEntity.ok(personService.findById(personId));
    }
}
