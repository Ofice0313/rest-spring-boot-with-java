package com.devcaleb.rest.controllers;

import com.devcaleb.rest.data.dto.v1.PersonDTO;
import com.devcaleb.rest.data.dto.v2.PersonDTOV2;
import com.devcaleb.rest.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(value = "/people")
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public PersonDTO findById(@PathVariable("id") Long id) {
        var person = service.findById(id);
        person.setBirthDay(new Date());
        //person.setPhoneNumber("+258 87 731 2374");
        person.setPhoneNumber("");
        person.setLastName(null);
        person.setSensitiveData("Food's Bar");
        return person;
    }

    @PostMapping
    public PersonDTO insert(@RequestBody PersonDTO person) {
        return service.create(person);
    }

//    @PostMapping(value = "/v2")
//    public PersonDTOV2 insert(@RequestBody PersonDTOV2 person) {
//        return service.createV2(person);
//    }

    @PutMapping
    public PersonDTO update(@RequestBody PersonDTO person) {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
