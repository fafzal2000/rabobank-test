package com.rabobank.assignment.exception;

public class NonUniqueException extends RuntimeException {

	public NonUniqueException() {
		super("FirstName & LastName must be unique");
	}
}
