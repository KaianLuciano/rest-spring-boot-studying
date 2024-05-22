package br.com.rest.spring.service;

import br.com.rest.spring.controller.PersonController;
import br.com.rest.spring.data.vo.v1.PersonVO;
import br.com.rest.spring.data.vo.v2.PersonVOV2;
import br.com.rest.spring.mapper.DozerMapper;
import br.com.rest.spring.mapper.custom.PersonMapper;
import br.com.rest.spring.model.Person;
import br.com.rest.spring.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@Service
public class PersonServiceImpl {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper = new PersonMapper();

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonVO findById(Long id) {
        log.info("Find person");
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person not found"));
        PersonVO personVO = DozerMapper.parseObject(person, PersonVO.class);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    public List<PersonVO> findAll() {
        log.info("Find all persons");
        List<PersonVO> personVOS = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        personVOS.forEach(personVO -> personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getPersonId())).withSelfRel()));
        return personVOS;
    }

    public PersonVO save(PersonVO person) {
        log.info("Save person");
        Person personEntity = DozerMapper.parseObject(person, Person.class);
        PersonVO personVO =  DozerMapper.parseObject(personRepository.save(personEntity), PersonVO.class);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getPersonId())).withSelfRel());
        return personVO;
    }

    public PersonVOV2 saveV2(PersonVOV2 person) {
        log.info("Save person");
        Person personEntity = personMapper.convertVoTOEntity(person);
        PersonVOV2 personVOV2 =  personMapper.convertEntityToVo(personRepository.save(personEntity));
        personVOV2.add(linkTo(methodOn(PersonController.class).findById(personVOV2.getPersonId())).withSelfRel());
        return personVOV2;
    }

    public PersonVO update(Long id, PersonVO person) {
        log.info("Update person");
        PersonVO personToUpdate = DozerMapper.parseObject(personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person not found")), PersonVO.class);
        personToUpdate.setAddress(person.getAddress());
        personToUpdate.setGender(person.getGender());
        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());
        PersonVO personVO =  DozerMapper.parseObject(personRepository.save(DozerMapper.parseObject(person, Person.class)), PersonVO.class);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getPersonId())).withSelfRel());
        return personVO;
    }

    public void delete(Long id) {
        log.info("Delete person");
        personRepository.deleteById(id);
    }
}
