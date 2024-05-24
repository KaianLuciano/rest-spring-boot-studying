package br.com.rest.spring.unit.tests.mockito.services;

import br.com.rest.spring.data.vo.v1.PersonVO;
import br.com.rest.spring.model.Person;
import br.com.rest.spring.repository.PersonRepository;
import br.com.rest.spring.service.impl.PersonServiceImpl;
import br.com.rest.spring.unit.tests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            personServiceImpl.save(null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
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
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            personServiceImpl.update(null, null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


}
