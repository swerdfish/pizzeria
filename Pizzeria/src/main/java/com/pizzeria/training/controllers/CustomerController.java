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

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService custServ;
	
	public CustomerController() {
	}
	
	@Autowired
	public CustomerController(CustomerService custServ) {
		super();
		this.custServ = custServ;
	}

	@GetMapping("/test")
	public String getAllOrders(){
		return "Customer Endpoint works";
	}
	
	// CREATE
	
	@PostMapping
	public Customer newCustomer(@RequestBody Customer newCustomer) {
	    return custServ.save(newCustomer);
	}
	
	// READ
	 
	@GetMapping
	public List<Customer> getAllCustomer(@RequestParam(required=false) ObjectId _id, @RequestParam(required = false) String city) {
		if (_id != null) return Collections.singletonList(custServ.getCustomerBy_id(_id));
		if (city != null) return custServ.getAllByCity(titlecase(city));
		return custServ.findAll();
	}
	
	@PostMapping("/examples")
	public List<Customer> getAllCustomersByExample(@RequestBody Customer customer) {
		return custServ.findAllByExample(customer);
	}
	
	// UPDATE
	
	@PutMapping
	public Customer updateCustomer(@RequestParam ObjectId _id, @RequestBody Customer updatedCustomer) {
		updatedCustomer.set_id(_id);
		return custServ.save(updatedCustomer);
	}
	
	// DELETE
	
	@DeleteMapping
	public void deleteCustomer(@RequestParam ObjectId _id) {
		Customer customerToDelete = custServ.getCustomerBy_id(_id);
		custServ.delete(customerToDelete);
	}
	
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
