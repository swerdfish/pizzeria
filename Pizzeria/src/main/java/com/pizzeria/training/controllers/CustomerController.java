package com.pizzeria.training.controllers;

import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
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
	public String getAllOrders(){
		return "Customer Endpoint works";
	}
	
	// CREATE
	/**
	 * Post method to add a customer to the database
	 * @param newCustomer New customer object to be added
	 * @return Saves new customer to Database
	 */
	@PostMapping
	public Customer newCustomer(@RequestBody Customer newCustomer) {
	    return custServ.save(newCustomer);
	}
	
	// READ
	 /**
	  * Get method for all customers in the database
	  * @param _id Optional parameter to filter by customer Id
	  * @param city Optional parameter to filter by city of residence
	  * @return List of all customers in the database
	  */
	@GetMapping
	public List<Customer> getAllCustomer(@RequestParam(required=false) ObjectId _id, @RequestParam(required = false) String city) {
		if (_id != null) return Collections.singletonList(custServ.getCustomerBy_id(_id));
		if (city != null) return custServ.getAllByCity(titlecase(city));
		return custServ.findAll();
	}
	/**
	 * Get method for customers using an example customer object
	 * @param customer The customer to be used as an example
	 * @return Customers matching the given example
	 */
	@PostMapping("/examples")
	public List<Customer> getAllCustomersByExample(@RequestBody Customer customer) {
		return custServ.findAllByExample(customer);
	}
	
	// UPDATE
	/**
	 * Put method for updating customer information
	 * @param _id MongoDB document Id to identify customer to edit
	 * @param updatedCustomer New customer object to replace the one currently under the Id
	 * @return Saves updated customer in the database
	 */
	@PutMapping
	public Customer updateCustomer(@RequestParam ObjectId _id, @RequestBody Customer updatedCustomer) {
		updatedCustomer.set_id(_id);
		return custServ.save(updatedCustomer);
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
		
//Sample input postman
//		{
//		    "phoneNum": 123141,
//		    "address": {
//				"streetAddress":"123 West st",
//				"streetAddress2":"",
//				"city":"Chicago",
//				"state":"IL",
//				"postal":"60111"
//			},
//		    "email":"sample@mail.com",
//		    "creditCard": {
//				"cardNumber":1234123412341234,
//				"expiration":"05/25",
//				"securityCode":123
//			}
//		}
		
}
