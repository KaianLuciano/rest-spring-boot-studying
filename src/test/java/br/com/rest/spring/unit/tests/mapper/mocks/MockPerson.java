package br.com.rest.spring.unit.tests.mapper.mocks;

import br.com.rest.spring.data.vo.v1.PersonVO;
import br.com.rest.spring.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonVO mockVO() {
        return mockVO(0);
    }



}
