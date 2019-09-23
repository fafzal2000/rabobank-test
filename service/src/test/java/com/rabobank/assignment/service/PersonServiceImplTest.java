package com.rabobank.assignment.service;

import com.rabobank.assignment.exception.NonUniqueException;
import com.rabobank.assignment.model.domain.PersonEntity;
import com.rabobank.assignment.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;


@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {

	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	PersonService personService = new PersonServiceImpl();

	@Before
	public void setUp() {

		PersonEntity personEntity = new PersonEntity();

		personEntity.setAge(10.0);
		personEntity.setFirstname("Faisal");
		personEntity.setLastname("Afzal");
		personEntity.setDob(new Date());

		doReturn(personEntity).when(personRepository).save(any(PersonEntity.class));
		doReturn(personEntity).when(personRepository).findByFirstnameAndLastname(eq("Faisal"), eq("Afzal"));
		doReturn(Optional.of(personEntity)).when(personRepository).findById(anyInt());
	}

	@Test
	public void whenPersonFindById_thenReturnPerson() {

		PersonEntity personEntity = personService.findById(1);

		assertThat(personEntity.getFirstname(), equalTo("Faisal"));
		assertThat(personEntity.getLastname(), equalTo("Afzal"));
		assertThat(personEntity.getAge().toString(), equalTo("10.0"));

	}


	@Test
	public void whenPersonNotExistsCreate_thenPersists() {

		PersonEntity personEntity = new PersonEntity();

		personEntity.setAge(10.0);
		personEntity.setFirstname("Nehal");
		personEntity.setLastname("Afzal");
		personEntity.setDob(new Date());

		doReturn(null).when(personRepository).findByFirstnameAndLastname(eq("Nehal"), eq("Afzal"));

		personService.create(personEntity);

		verify(personRepository).save(any(PersonEntity.class));


	}


	@Test(expected = NonUniqueException.class)
	public void whenPersonAlreadyExistsCreate_thenReturnNonUniqueException() {

		PersonEntity personEntity = new PersonEntity();

		personEntity.setAge(10.0);
		personEntity.setFirstname("Faisal");
		personEntity.setLastname("Afzal");
		personEntity.setDob(new Date());

		personService.create(personEntity);

		verifyZeroInteractions(personRepository.save(any(PersonEntity.class)));

	}

	@Test
	public void whenPersonUpdate_thenUpdatePersonRecord() {

		PersonEntity personEntity = new PersonEntity();

		personEntity.setId(1);
		personEntity.setAge(10.0);
		personEntity.setFirstname("Faisal");
		personEntity.setLastname("Afzal");
		personEntity.setDob(new Date());

		personService.update(personEntity);

		verify(personRepository).save(any(PersonEntity.class));

	}

}