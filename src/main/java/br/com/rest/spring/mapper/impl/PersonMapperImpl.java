package br.com.rest.spring.mapper.impl;

import br.com.rest.spring.data.vo.v1.PersonVO;
import br.com.rest.spring.mapper.PersonMapper;
import br.com.rest.spring.model.Person;

public class PersonMapperImpl implements PersonMapper {
    @Override
    public Person userVOToEntity(PersonVO personVO) {
        return null;
    }

    @Override
    public PersonVO userEntityToVO(Person personEntity) {
        return null;
    }
}
