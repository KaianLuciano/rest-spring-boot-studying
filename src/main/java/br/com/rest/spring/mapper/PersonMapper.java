package br.com.rest.spring.mapper;

import br.com.rest.spring.data.vo.v1.PersonVO;
import br.com.rest.spring.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    Person userVOToEntity(PersonVO personVO);
    PersonVO userEntityToVO(Person personEntity);
}
