package com.rabobank.assignment.repository;


import com.rabobank.assignment.Application;
import com.rabobank.assignment.model.domain.PersonEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PersonRepositoryRepositoryTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void GivenPersonWhenSaveThenPersists() {

		PersonEntity personEntity = new PersonEntity();

		personEntity.setAge(10.0);
		personEntity.setFirstname("Faisal");
		personEntity.setLastname("Afzal");
		personEntity.setDob(new java.util.Date());

		PersonEntity entity = personRepository.save(personEntity);

		java.util.Optional<PersonEntity> entityOptional = personRepository.findById(entity.getId());

		assertTrue(entityOptional.isPresent());

	}


	@Test
	public void GivenPersonWhenFindByFirstnameAndLastnameThenReturnPerson() {

		PersonEntity personEntity = new PersonEntity();

		personEntity.setAge(10.0);
		personEntity.setFirstname("Faisal");
		personEntity.setLastname("Afzal");
		personEntity.setDob(new java.util.Date());

		personRepository.save(personEntity);

		PersonEntity entity = personRepository.findByFirstnameAndLastname("Faisal", "Afzal");

		assertThat(entity, is(notNullValue()));
	}

}
