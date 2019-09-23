package com.rabobank.assignment.service;


import com.rabobank.assignment.model.domain.PersonEntity;

import java.util.List;

public interface PersonService {

	List<PersonEntity> getPersons();

	PersonEntity create(PersonEntity person);

	PersonEntity findById(Integer id);

	PersonEntity update(PersonEntity person);
}
