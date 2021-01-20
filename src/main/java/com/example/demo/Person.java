package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="persons")
public class Person {
	@Id
	private String id;
	@Column
	private String first_name;
	@Column
	private String last_name;
	@Column
	private String street;
	@Column
	private String city;
	@Column
	private String state;
	@Column
	private String postal_code;
	
	public Person()
	{
		
	}

	public Person(String id, String first_name, String last_name, String street, String city, String state,
			String postal_code) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postal_code = postal_code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	

}
