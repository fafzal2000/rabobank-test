package com.rabobank.assignment.service;


import com.google.common.collect.ImmutableList;
import com.rabobank.assignment.exception.NonUniqueException;
import com.rabobank.assignment.exception.PersonNotFoundException;
import com.rabobank.assignment.model.domain.PersonEntity;
import com.rabobank.assignment.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<PersonEntity> getPersons() {
		return ImmutableList.copyOf(personRepository.findAll());
	}

	@Override
	public PersonEntity create(PersonEntity person) {
		PersonEntity personEntity = personRepository.findByFirstnameAndLastname(person.getFirstname(), person.getLastname());
		if (personEntity != null) {
			throw new NonUniqueException();
		}
		return personRepository.save(person);
	}

	@Override
	public PersonEntity findById(Integer id) {
		Optional<PersonEntity> person = personRepository.findById(id);
		if (!person.isPresent()) {
			log.error("Id " + id + " is not existed");
			throw new PersonNotFoundException(id);
		}
		return person.get();
	}


	@Override
	public PersonEntity update(PersonEntity person) {

		Optional<PersonEntity> entity = personRepository.findById(person.getId());
		if (!entity.isPresent()) {
			log.error("Id " + person.getId() + " is not existed");
			throw new PersonNotFoundException(person.getId());
		}
		return personRepository.save(person);
	}
}
