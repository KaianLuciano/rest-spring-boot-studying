package br.com.rest.spring.service.impl;

import br.com.rest.spring.controller.PersonController;
import br.com.rest.spring.data.vo.v1.PersonVO;
import br.com.rest.spring.data.vo.v2.PersonVOV2;
import br.com.rest.spring.exception.handler.exception.Exceptions;
import br.com.rest.spring.mapper.DozerMapper;
import br.com.rest.spring.mapper.custom.PersonMapper;
import br.com.rest.spring.model.Person;
import br.com.rest.spring.repository.PersonRepository;
import br.com.rest.spring.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper = new PersonMapper();

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonVO findById(Long id) {
        log.info("Find person");
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new Exceptions.ResourceNotFoundException("Person not found"));
        PersonVO personVO = DozerMapper.parseObject(person, PersonVO.class);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    @Override
    public List<PersonVO> findAll() {
        log.info("Find all persons");
        List<PersonVO> personVOS = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        personVOS.forEach(personVO -> personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getPersonId())).withSelfRel()));
        return personVOS;
    }

    @Override
    @Transactional
    public PersonVO save(PersonVO person) {
        if(person == null)
            throw new Exceptions.RequiredObjectIsNullException();
        log.info("Save person");
        Person personEntity = DozerMapper.parseObject(person, Person.class);
        PersonVO personVO =  DozerMapper.parseObject(personRepository.save(personEntity), PersonVO.class);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getPersonId())).withSelfRel());
        return personVO;
    }

    @Override
    @Transactional
    public PersonVOV2 saveV2(PersonVOV2 person) {
        if(person == null)
            throw new Exceptions.RequiredObjectIsNullException();
        log.info("Save person");
        Person personEntity = personMapper.convertVoTOEntity(person);
        PersonVOV2 personVOV2 =  personMapper.convertEntityToVo(personRepository.save(personEntity));
        personVOV2.add(linkTo(methodOn(PersonController.class).findById(personVOV2.getPersonId())).withSelfRel());
        return personVOV2;
    }

    @Override
    @Transactional
    public PersonVO update(Long id, PersonVO person) {
        if(person == null)
            throw new Exceptions.RequiredObjectIsNullException();
        log.info("Update person");
        PersonVO personToUpdate = DozerMapper.parseObject(personRepository.findById(id)
                .orElseThrow(() -> new Exceptions.ResourceNotFoundException("Person not found")), PersonVO.class);
        personToUpdate.setAddress(person.getAddress());
        personToUpdate.setGender(person.getGender());
        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());
        PersonVO personVO =  DozerMapper.parseObject(personRepository.save(DozerMapper.parseObject(person, Person.class)), PersonVO.class);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getPersonId())).withSelfRel());
        return personVO;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Delete person");
        PersonVO personToUpdate = DozerMapper.parseObject(personRepository.findById(id)
                .orElseThrow(() -> new Exceptions.ResourceNotFoundException("Person not found")), PersonVO.class);
        personRepository.delete(DozerMapper.parseObject(personToUpdate, Person.class));
    }
}


