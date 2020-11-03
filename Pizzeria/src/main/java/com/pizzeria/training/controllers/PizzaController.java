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

import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.repository.PizzaRepository;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

	@Autowired
	private PizzaRepository repository;
	
	@GetMapping("/test")
	public @ResponseBody String getAllOrders(){
		
		return "Endpoint works v3";
	}
	 
	@RequestMapping(value = "/getPizza", method = RequestMethod.GET)
	public List<Pizza> getAllCustomer() {
	  return repository.findAll();
	}
	

	@PostMapping("/addPizza")
	  Pizza newCustomer(@RequestBody Pizza newPizza) {
	    return repository.save(newPizza);
	  }
	
	//Sample input for postman
//	{
//	    "height": 13.0,
//	    "type": "CLASSIC",
//	    "toppings" : [
//	             "BACON",
//	             "PINEAPPLE"
//	    ],
//	    "cost" : 12,
//	    "size" : "SMALL"
//	}
}
