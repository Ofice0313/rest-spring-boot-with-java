package com.devcaleb.rest_spring_boot_with_java.repositories;

import com.devcaleb.rest_spring_boot_with_java.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
