package com.devcaleb.rest_spring_boot_with_java.services;

import com.devcaleb.rest_spring_boot_with_java.data.dto.PersonDTO;
import com.devcaleb.rest_spring_boot_with_java.exceptions.ResourceNotFoundException;
import com.devcaleb.rest_spring_boot_with_java.mapper.ObjectMapper;
import com.devcaleb.rest_spring_boot_with_java.model.Person;
import com.devcaleb.rest_spring_boot_with_java.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<PersonDTO> findAll() {
        return ObjectMapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        return ObjectMapper.parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {

        var entity = ObjectMapper.parseObject(person, Person.class);
        return ObjectMapper.parseObject(personRepository.save(entity), PersonDTO.class);
    }

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
