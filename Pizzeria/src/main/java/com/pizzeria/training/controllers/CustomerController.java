package com.pizzeria.training.controllers;

import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.training.models.Customer;
import com.pizzeria.training.service.CustomerService;
/**
 * REST controller class for Customer object to interact with mongoDB database
 */
@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin
@CrossOrigin(origins = "http://mypizzeriaapp.com.s3-website.us-east-2.amazonaws.com")
@RequestMapping("/customers")
public class CustomerController {
	/**
	 * Service to route requests to repository
	 */
	private CustomerService custServ;
	/** No argument constructor */
	public CustomerController() {
	}
	/**
	 * Parameterized Constructor
	 * @param custServ Service to route requests
	 */
	@Autowired
	public CustomerController(CustomerService custServ) {
		super();
		this.custServ = custServ;
	}
	/**
	 * Test endpoint
	 * @return Message to indicate the Customer endpoint is functional
	 */
	@GetMapping("/test")
	public ResponseEntity<String> test(){
		return new ResponseEntity<String>("Customer Endpoint works", HttpStatus.OK);
	}
	
	// CREATE
	/**
	 * Post method to add a customer to the database
	 * @param newCustomer New customer object to be added
	 * @return Saves new customer to Database
	 */
	@PostMapping
	public ResponseEntity<Customer> newCustomer(@RequestBody Customer newCustomer) throws IllegalArgumentException {
	    return new ResponseEntity<Customer>(custServ.save(newCustomer), HttpStatus.CREATED);
	}
	
	// READ
	 /**
	  * Get method for all customers in the database
	  * @param _id Optional parameter to filter by customer Id
	  * @param city Optional parameter to filter by city of residence
	  * @return List of all customers in the database
	  */
	@GetMapping
	public ResponseEntity<List<Customer>> getCustomers(@RequestParam(required=false) ObjectId _id, @RequestParam(required = false) String city) {
		if (_id != null) return new ResponseEntity<>(Collections.singletonList(custServ.getCustomerBy_id(_id)), HttpStatus.OK);
		if (city != null) return new ResponseEntity<>(custServ.getAllByCity(titlecase(city)), HttpStatus.OK);
		return new ResponseEntity<>(custServ.findAll(), HttpStatus.OK);
	}
	/**
	 * Get method for customers using an example customer object
	 * @param customer The customer to be used as an example
	 * @return Customers matching the given example
	 */
	@PostMapping("/examples")
	public ResponseEntity<List<Customer>> getAllCustomersByExample(@RequestBody Customer customer) {
		return new ResponseEntity<>(custServ.findAllByExample(customer), HttpStatus.OK);
	}
	
	@GetMapping("/email")
	public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String email){
		return new ResponseEntity<>(custServ.findByEmail(email), HttpStatus.OK);
	}
	// UPDATE
	/**
	 * Put method for updating customer information
	 * @param _id MongoDB document Id to identify customer to edit
	 * @param updatedCustomer New customer object to replace the one currently under the Id
	 * @return Saves updated customer in the database
	 */
	@PutMapping
	public ResponseEntity<Customer> updateCustomer(@RequestParam ObjectId _id, @RequestBody Customer updatedCustomer) throws IllegalArgumentException {
		updatedCustomer.set_id(_id);
		return new ResponseEntity<Customer>(custServ.save(updatedCustomer), HttpStatus.OK);
	}
	
	// DELETE
	/**
	 * Delete method for removing a customer from the database
	 * @param _id The customer to be deleted's Id
	 */
	@DeleteMapping
	public void deleteCustomer(@RequestParam ObjectId _id) {
		Customer customerToDelete = custServ.getCustomerBy_id(_id);
		custServ.delete(customerToDelete);
	}
	/**
	 * Returns a string formatted to have the first letter of each word Capitalized and the rest lowercase
	 * @param input The string to be modified
	 * @return The correctly cased string
	 */
	private String titlecase(String input) {
		StringBuilder outputSb = new StringBuilder();
		
		for (String inp : input.split(" ")) {
			outputSb.append(Character.toUpperCase(input.charAt(0)));
			for (int i=1; i<inp.length(); i++) {
				outputSb.append(Character.toLowerCase(input.charAt(i)));
			}
			outputSb.append(" ");
		}
		
		return outputSb.toString().trim();
	}	
}
/*

{
	"email":"mail@mail.com",
	"password":"password",
	"firstName":"first",
	"lastName":"last",
	"phoneNum":1231231234,
	"homeAddress": {
		"streetAddress":"1 home",
		"streetAddressLine2":"2 home",
		"city":"city",
		"state":"state",
		"postal":"postal"
	},
	"card": {
		"cardNumber":1234123412341234,
		"expiration":"MM/YY",
		"securityCode":123,
		"billingAddress": {
			"streetAddress":"1 billing",
			"streetAddressLine2":"2 billing",
			"city":"city",
			"state":"state",
			"postal":"postal"
		}
	},
	"favoriteOrder": [
		{
			"type": "CLASSIC",
			"toppings": [
				"PEPPERONI", "SAUSAGE"
			],
			"cost": 10.0,
			"size": "LARGE"
		}
	]
}

 */
