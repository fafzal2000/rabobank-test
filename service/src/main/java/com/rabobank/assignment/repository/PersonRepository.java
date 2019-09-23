package com.rabobank.assignment.repository;


import com.rabobank.assignment.model.domain.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {

	PersonEntity findByFirstnameAndLastname(String firstName, String lastName);

}
