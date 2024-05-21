package br.com.rest.spring.service;

import br.com.rest.spring.data.vo.v1.PersonVO;
import br.com.rest.spring.data.vo.v2.PersonVOV2;
import br.com.rest.spring.model.Person;
import br.com.rest.spring.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonVO findById(Long id) {
        log.info("Find person");
        return modelMapper.map(personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person not found")), PersonVO.class);
    }

    public List<PersonVO> findAll() {
        log.info("Find all persons");
        return personRepository.findAll().stream().map(person -> modelMapper.map(person, PersonVO.class)).toList();
    }

    public PersonVO save(PersonVO person) {
        log.info("Save person");
        return modelMapper.map(personRepository.save(modelMapper.map(person, Person.class)), PersonVO.class);
    }

    public PersonVOV2 saveV2(PersonVOV2 person) {
        log.info("Save person");
        return modelMapper.map(personRepository.save(modelMapper.map(person, Person.class)), PersonVOV2.class);
    }

    public PersonVO update(Long id, PersonVO person) {
        log.info("Update person");
        PersonVO personToUpdate = modelMapper.map(personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person not found")), PersonVO.class);
        personToUpdate.setAddress(person.getAddress());
        personToUpdate.setGender(person.getGender());
        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());
        return modelMapper.map(personRepository.save(modelMapper.map(person, Person.class)), PersonVO.class);
    }

    public void delete(Long id) {
        log.info("Delete person");
        personRepository.deleteById(id);
    }
}
