package com.devcaleb.rest_spring_boot_with_java.data.dto;

import java.util.Objects;

public class PersonDTO {

    private Long id;
    private String firstName;
    private String address;
    private String gender;
    private String lastName;

    public PersonDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(id, personDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
