package com.rabobank.assignment.api.rest.integration;

import com.rabobank.assignment.api.rest.PersonController;
import com.rabobank.assignment.model.domain.PersonEntity;
import com.rabobank.assignment.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PersonService service;

	private PersonEntity personEntity;

	@Before
	public void setUp() {

		personEntity = new PersonEntity();
		personEntity.setAge(10.0);
		personEntity.setFirstname("Nehal");
		personEntity.setLastname("Afzal");
		personEntity.setDob(new Date());

		service.create(personEntity);

	}

	@Test
	public void whenPersons_thenReturnAllPersons()
			throws Exception {

		given(service.getPersons()).willReturn(Arrays.asList(personEntity));

		mvc.perform(get("/persons")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].firstname", equalTo("Nehal")))
				.andExpect(jsonPath("$[0].lastname", equalTo("Afzal")))
				.andExpect(jsonPath("$[0].age", is(10.0)));
	}

	@Test
	public void whenPersonCreate_thenReturnOkStatusCode()
			throws Exception {

		given(service.getPersons()).willReturn(Arrays.asList(personEntity));


		mvc.perform(post("/persons").content("{\n" +
				"    \"id\": 1,\n" +
				"    \"firstname\": \"Nehal\",\n" +
				"    \"lastname\": \"Afzal\",\n" +
				"    \"dob\": \"01-02-1981\",\n" +
				"    \"age\": 20,\n" +
				"    \"address\": \"La Palma 89\"\n" +
				"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void whenUserAuthenticatedAndPersonUpdate_thenReturnOkStatusCode()
			throws Exception {

		PersonEntity personEntity = new PersonEntity();

		personEntity.setAge(20.0);
		personEntity.setFirstname("Faisal");
		personEntity.setLastname("Afzal");

		doReturn(personEntity).when(service).update(any());

		mvc.perform(put("/persons").content("{\n" +
				"    \"id\": 1,\n" +
				"    \"firstname\": \"Faisal\",\n" +
				"    \"lastname\": \"Afzal\",\n" +
				"    \"dob\": \"01-02-1981\",\n" +
				"    \"age\": 20,\n" +
				"    \"address\": \"La Palma 89\"\n" +
				"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(authenticated())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstname", equalTo("Faisal")))
				.andExpect(jsonPath("$.lastname", equalTo("Afzal")))
				.andExpect(jsonPath("$.age", is(20.0)));
	}

}