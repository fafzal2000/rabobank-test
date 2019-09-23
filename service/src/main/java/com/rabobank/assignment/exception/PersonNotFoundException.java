package com.rabobank.assignment.exception;

public class PersonNotFoundException extends RuntimeException {

	public PersonNotFoundException(Integer id) {
		super("Person not found : " + id);
	}
}
