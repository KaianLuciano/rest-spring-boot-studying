package br.com.rest.spring.data.vo.v1;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class PersonVO extends RepresentationModel<PersonVO> {

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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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
