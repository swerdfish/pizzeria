package com.pizzeria.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.training.models.Customer;
import com.pizzeria.training.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

		@Autowired
		private CustomerRepository repository;
		
		
		@GetMapping("/test")
		public @ResponseBody String getAllOrders(){
			
			return "Endpoint works v2";
		}
		 
		@RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
		public List<Customer> getAllCustomer() {
		  return repository.findAll();
		}
		
		@PostMapping("/addCustomer")
		  Customer newCustomer(@RequestBody Customer newCustomer) {
		    return repository.save(newCustomer);
		  }

//Sample input postman
//		{
//		    "id" : 123141,
//		    "phoneNum": 123141,
//		    "email": "teodelson",
//		    "address": "Sampleinasd",
//		    "creditCard":1111
//		}
		
		
}
