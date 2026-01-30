package com.devcaleb.rest.integrationtests.dto.wrappers;


import com.devcaleb.rest.integrationtests.dto.PersonDTO;
import com.devcaleb.rest.model.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PersonEmbeddedDTO {

    @JsonProperty("people")
    private List<PersonDTO> people;

    public List<PersonDTO> getPeople() {
        return people;
    }

    public void setPeople(List<PersonDTO> people) {
        this.people = people;
    }
}
