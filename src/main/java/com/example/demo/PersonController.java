package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PersonController {
	
	static List<Person> personList = new ArrayList<Person>();
	
	@Autowired
	PersonRepository personRepository;
	
	@GetMapping("/getAllPersons")
	public List<Person> getAllPersons()
	{
		
		personList = personRepository.findAll();
		
		return personList;
	}
	
	@GetMapping("/getCountofPersons")
	public int getCountPersons()
	{
		 return personList.size();
	}
	
	
	
	@GetMapping("/getPersonById/{id}")
	public Optional<Person> getPersonById(@PathVariable(value="id") String id)
	{
		return personRepository.findById(id);
	}
	
	@PostMapping("/addPerson")
	public Person addPerson(@RequestBody Person person)
	{
		return personRepository.save(person);
	}
	
	@PutMapping("/updatePerson/{id}")
	public Person updatePerson(@PathVariable(value="id") String id, @RequestBody Person personDetails)
	{
		
		
		Optional<Person> person = personRepository.findById(id);
		Person newPerson=person.get();
		newPerson.setFirst_name(personDetails.getFirst_name());		
		newPerson.setLast_name(personDetails.getLast_name());
		newPerson.setCity(personDetails.getCity());
		newPerson.setState(personDetails.getState());
		newPerson.setPostal_code(personDetails.getPostal_code());
		newPerson.setStreet(personDetails.getStreet());
		return personRepository.save(newPerson);
		
	}
	
	@DeleteMapping("/deletePerson/{id}")
	public void deletePerson(@PathVariable(value="id") String id)
	{
		Optional<Person> person=personRepository.findById(id);
		Person newPerson=person.get();
		personRepository.delete(newPerson);
	}
	

}
