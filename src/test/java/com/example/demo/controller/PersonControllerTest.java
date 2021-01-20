package com.example.demo.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.Person;
import com.example.demo.PersonController;


@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonController personController;

	Person mockPerson = new Person("23","Michael", "Umpress", "78-Downing","Letterkenny","Ulster","F87 V67");

	String examplePerson = "\"{\\\"id\\\": \\\"78\\\",\" + \r\n" + 
			"				\"        \\\"firstName\\\": \\\"KARTIK\\\",\" + \r\n" + 
			"				\"        \\\"lastName\\\": \\\"Alakija\\\",\" + \r\n" + 
			"				\"        \\\"street\\\": \\\"5467\\\",\" + \r\n" + 
			"				\"		\\\"city\\\": \\\"5467\\\",\" + \r\n" + 
			"				\"		\\\"state\\\": \\\"5467\\\",\" + \r\n" + 
			"				\"		\\\"postalCode\\\": \\\"5467\\\"}\"";

	@SuppressWarnings("unchecked")
	@Test
	public void retrieveDetailsForPerson() throws Exception {

		Mockito.when(
				personController.getAllPersons()).thenReturn((List<Person>) mockPerson);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/getAllPersons").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"id\": \"78\"," + 
				"        \"firstName\": \"KARTIK\"," + 
				"        \"lastName\": \"Alakija\"," + 
				"        \"street\": \"5467\"," + 
				"		\"city\": \"5467\"," + 
				"		\"state\": \"5467\"," + 
				"		\"postalCode\": \"5467\"}";


		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void createPersonDetails() throws Exception {
		Person mockPerson= new Person("23","Michael", "Umpress", "78-Downing","Letterkenny","Ulster","F87 V67");

		Mockito.when(
				personController.addPerson(Mockito.any(Person.class))).thenReturn(mockPerson);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addPerson")
				.accept(MediaType.APPLICATION_JSON).content(examplePerson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost:9091/addPerson",
				response.getHeader(HttpHeaders.LOCATION));

	}

}