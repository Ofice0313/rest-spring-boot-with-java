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
        var people = ObjectMapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
        people.forEach(p -> addHateoasLinks(p));
        return people;
    }

    public PersonDTO findById(Long id) {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        var dto = ObjectMapper.parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO create(PersonDTO person) {

        var entity = ObjectMapper.parseObject(person, Person.class);
        var dto = ObjectMapper.parseObject(personRepository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
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

        var dto = ObjectMapper.parseObject(personRepository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        personRepository.delete(entity);
    }

    private static void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).insert(dto)).withRel("insert").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
