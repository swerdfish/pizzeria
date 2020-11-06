package com.pizzeria.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.training.models.Customer;
import com.pizzeria.training.service.CustomerService;

@RestController
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
		public @ResponseBody String getAllOrders(){
			return "Customer Endpoint works";
		}
		 
		@RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
		public @ResponseBody List<Customer> getAllCustomer() {
		  return custServ.findAll();
		}
		
		@PostMapping("/addCustomer")
		 public @ResponseBody Customer newCustomer(@RequestBody Customer newCustomer) {
		    return custServ.save(newCustomer);
		}

//Sample input postman
//		{
//		    "id" : 123141,
//		    "phoneNum": 123141,
//		    "address": {
//				"streetAddress":"123 West st",
//				"streetAddress2":"",
//				"city":"Chicago",
//				"state":"IL",
//				"postal":"60111"
//			},
//		    "email":"sample@mail.com",
//		    "creditCard":1111
//		}
		
		
}
