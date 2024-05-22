package br.com.rest.spring.service;

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

@Slf4j
@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper = new PersonMapper();

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonVO findById(Long id) {
        log.info("Find person");
        return DozerMapper.parseObject(personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person not found")), PersonVO.class);
    }

    public List<PersonVO> findAll() {
        log.info("Find all persons");
        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO save(PersonVO person) {
        log.info("Save person");
        Person personEntity = DozerMapper.parseObject(person, Person.class);
        return DozerMapper.parseObject(personRepository.save(personEntity), PersonVO.class);
    }

    public PersonVOV2 saveV2(PersonVOV2 person) {
        log.info("Save person");
        var entity = personMapper.convertVoTOEntity(person);
        return personMapper.convertEntityToVo(personRepository.save(entity));
    }

    public PersonVO update(Long id, PersonVO person) {
        log.info("Update person");
        PersonVO personToUpdate = DozerMapper.parseObject(personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person not found")), PersonVO.class);
        personToUpdate.setAddress(person.getAddress());
        personToUpdate.setGender(person.getGender());
        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());
        return DozerMapper.parseObject(personRepository.save(DozerMapper.parseObject(person, Person.class)), PersonVO.class);
    }

    public void delete(Long id) {
        log.info("Delete person");
        personRepository.deleteById(id);
    }
}
