package br.com.rest.spring.unittests.mockito.services;

import br.com.rest.spring.data.vo.v1.PersonVO;
import br.com.rest.spring.exception.handler.exception.Exceptions;
import br.com.rest.spring.model.Person;
import br.com.rest.spring.repository.PersonRepository;
import br.com.rest.spring.service.impl.PersonServiceImpl;
import br.com.rest.spring.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonServiceImpl personServiceImpl;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
    }

    @Test
    void testFindById() {
        Person entity = input.mockEntity(1);
        entity.setPersonId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = personServiceImpl.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getPersonId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreate() {
        Person entity = input.mockEntity(1);
        entity.setPersonId(1L);

        Person persisted = entity;
        persisted.setPersonId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setPersonId(1L);

        when(personRepository.save(entity)).thenReturn(persisted);

        var result = personServiceImpl.save(vo);

        assertNotNull(result);
        assertNotNull(result.getPersonId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(Exceptions.RequiredObjectIsNullException.class, () -> {
            personServiceImpl.save(null);
        });

        String expectedMessage = "It not possible to process the request because the required object is null.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdate() {
        Person entity = input.mockEntity(1);

        Person persisted = entity;
        persisted.setPersonId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setPersonId(1L);


        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(personRepository.save(entity)).thenReturn(persisted);

        var result = personServiceImpl.update(1L, vo);

        assertNotNull(result);
        assertNotNull(result.getPersonId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(Exceptions.RequiredObjectIsNullException.class, () -> {
            personServiceImpl.update(null, null);
        });

        String expectedMessage = "It not possible to process the request because the required object is null.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDelete() {
        Person entity = input.mockEntity(1);
        entity.setPersonId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));

        personServiceImpl.delete(1L);
    }

    @Test
    void testFindAll() {
        List<Person> list = input.mockEntityList();

        when(personRepository.findAll()).thenReturn(list);

        var people = personServiceImpl.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getPersonId());
        assertNotNull(personOne.getLinks());

        assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());

        var personFour = people.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getPersonId());
        assertNotNull(personFour.getLinks());

        assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
        assertEquals("Addres Test4", personFour.getAddress());
        assertEquals("First Name Test4", personFour.getFirstName());
        assertEquals("Last Name Test4", personFour.getLastName());
        assertEquals("Male", personFour.getGender());

        var personSeven = people.get(7);

        assertNotNull(personSeven);
        assertNotNull(personSeven.getPersonId());
        assertNotNull(personSeven.getLinks());

        assertTrue(personSeven.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
        assertEquals("Addres Test7", personSeven.getAddress());
        assertEquals("First Name Test7", personSeven.getFirstName());
        assertEquals("Last Name Test7", personSeven.getLastName());
        assertEquals("Female", personSeven.getGender());
    }

}
