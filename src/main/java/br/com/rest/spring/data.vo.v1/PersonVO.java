package br.com.rest.spring.data.vo.v1;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class PersonVO {

    private Long personId;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public PersonVO() {}

    public PersonVO(Long personId, String firstName, String lastName, String address, String gender) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonVO person)) return false;
        return Objects.equals(getPersonId(), person.getPersonId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonId());
    }
}
