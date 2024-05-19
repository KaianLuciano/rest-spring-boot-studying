package br.com.rest.spring.service;

import br.com.rest.spring.PersonRepository;
import br.com.rest.spring.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(Long id) {
        log.info("Find person");
        return personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Person not found"));
    }

    public List<Person> findAll() {
        log.info("Find all persons");
        return personRepository.findAll();
    }
}
