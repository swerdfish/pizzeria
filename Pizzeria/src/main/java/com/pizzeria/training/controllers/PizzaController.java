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

import com.pizzeria.training.models.Pizza;

//@RestController
//@RequestMapping("/pizzas")
//@CrossOrigin(origins = "http://localhost:3000")
//public class PizzaController {
//
//	@Autowired
//	private PizzaService pizzaServ;
//	
//	public PizzaController() {
//	}
//	
//	@Autowired
//	public PizzaController(PizzaService pizzaServ) {
//		super();
//		this.pizzaServ = pizzaServ;
//	}
//
//	@GetMapping("/test")
//	public String getAllOrders(){
//		
//		return "Pizza Endpoint works";
//	}
//	
//	// READ
//	
//	@GetMapping
//	public List<Pizza> getAllPizza(@RequestParam(required = false) ObjectId _id) {
//		if (_id != null) return Collections.singletonList(pizzaServ.findBy_id(_id));
//		return pizzaServ.findAll();
//	}
	 
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
//}
