package br.com.rest.spring.data.vo.v2;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class PersonVOV2 {

    private Long personId;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private Date birthDay;

    public PersonVOV2() {}

    public PersonVOV2(Long personId, String firstName, String lastName, String address, String gender, Date birthDay) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonVOV2 person)) return false;
        return Objects.equals(getPersonId(), person.getPersonId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonId());
    }
}
