package br.com.rest.spring.service;

import br.com.rest.spring.data.vo.v1.PersonVO;
import br.com.rest.spring.data.vo.v2.PersonVOV2;

import java.util.List;

public interface PersonService {
    PersonVO findById(Long id);
    List<PersonVO> findAll();
    PersonVO save(PersonVO person);
    PersonVOV2 saveV2(PersonVOV2 person);
    PersonVO update(Long id, PersonVO person);
    void delete(Long id);
}
