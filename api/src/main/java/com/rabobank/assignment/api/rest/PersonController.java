package com.rabobank.assignment.api.rest;


import com.google.common.collect.ImmutableList;
import com.rabobank.assignment.model.domain.PersonEntity;
import com.rabobank.assignment.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/persons")
@Slf4j
public class PersonController {

	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	public ResponseEntity<List<PersonEntity>> getAll() {
		return ResponseEntity.ok(ImmutableList.copyOf(personService.getPersons()));
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity create(@Valid @RequestBody PersonEntity person) {
		personService.create(person);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonEntity> get(@PathVariable Integer id) {
		PersonEntity person = personService.findById(id);
		return ResponseEntity.ok(person);
	}

	@PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity update(@Valid @RequestBody PersonEntity person) {
		PersonEntity personUpdate = personService.update(person);
		return ResponseEntity.ok(personUpdate);
	}
}
