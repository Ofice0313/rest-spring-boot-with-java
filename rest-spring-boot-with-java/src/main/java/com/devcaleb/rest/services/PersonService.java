package com.devcaleb.rest.services;

import com.devcaleb.rest.controllers.PersonController;
import com.devcaleb.rest.data.dto.v1.PersonDTO;
import com.devcaleb.rest.data.dto.v2.PersonDTOV2;
import com.devcaleb.rest.exceptions.ResourceNotFoundException;
import com.devcaleb.rest.mapper.ObjectMapper;
import com.devcaleb.rest.mapper.custom.PersonMapper;
import com.devcaleb.rest.model.Person;
import com.devcaleb.rest.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    PersonMapper converter;

    public List<PersonDTO> findAll() {
        return ObjectMapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        var dto = ObjectMapper.parseObject(entity, PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel().withType("GET"));
        return dto;
    }

    public PersonDTO create(PersonDTO person) {

        var entity = ObjectMapper.parseObject(person, Person.class);
        return ObjectMapper.parseObject(personRepository.save(entity), PersonDTO.class);
    }

//    public PersonDTOV2 createV2(PersonDTOV2 person) {
//
//        var entity = converter.convertDTOToEntity(person);
//        return converter.convertEntityToDTO(personRepository.save(entity));
//    }

    public PersonDTO update(PersonDTO person) {
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return ObjectMapper.parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        personRepository.delete(entity);
    }
}
