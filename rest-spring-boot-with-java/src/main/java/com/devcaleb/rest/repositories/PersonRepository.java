package com.devcaleb.rest.repositories;

import com.devcaleb.rest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
