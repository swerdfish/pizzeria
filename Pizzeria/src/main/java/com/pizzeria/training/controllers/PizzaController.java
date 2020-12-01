package com.pizzeria.training.controllers;

import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.pizzeria.training.models.Pizza;
/**
 * REST controller class for Pizza object to interact with mongoDB database
 */
//@RestController
//@RequestMapping("/pizzas")
//@CrossOrigin(origins = "http://localhost:3000")
//public class PizzaController {
//	/**
//	 * Service to route requests to repository
//	 */
//	@Autowired
//	private PizzaService pizzaServ;
//	/**
//	 * no argument constructor
//	 */
//	public PizzaController() {
//	}
//	/**
//	 * Parameterized constructor
//	 * @param pizzaServ Service to route requests
//	 */
//	@Autowired
//	public PizzaController(PizzaService pizzaServ) {
//		super();
//		this.pizzaServ = pizzaServ;
//	}
//	/**
//	 * Test endpoint
//	 * @return Message to indicate the Pizza endpoint is functional
//	 */
//	@GetMapping("/test")
//	public String getAllOrders(){
//		
//		return "Pizza Endpoint works";
//	}
//	
//	// CREATE
//	/**
//	 * Post mapping to add a new pizza to the database
//	 * @param newPizza New pizza object to be added
//	 * @return Saves new pizza to the database
//	 */
//	@PostMapping
//	public Pizza newPizza(@RequestBody Pizza newPizza) {
//	    return pizzaServ.save(newPizza);
//	}
//	
//	// READ
//	/**
//	 * Get method for all pizzas in the database
//	 * @param _id Optional parameter to filter py pizza ID
//	 * @return List of all pizzas in the database
//	 */
//	@GetMapping
//	public List<Pizza> getAllPizza(@RequestParam(required = false) ObjectId _id) {
//		if (_id != null) return Collections.singletonList(pizzaServ.findBy_id(_id));
//		return pizzaServ.findAll();
//	}
//	/**
//	 * Get method for pizzas using an example pizza object
//	 * @param pizza the pizza to be used as an example
//	 * @return Pizzas matching the given example
//	 */
//	// Passing in pizza request body filters by field, just type one field, and it should find the respective pizza
//	@PostMapping("/examples")
//	public List<Pizza> getAllPizzaByExample(@RequestBody Pizza pizza) {
//		return pizzaServ.getAllByExample(pizza);
//	}
//	
//	// UPDATE
//	/**
//	 * Put method for updating pizza information
//	 * @param _id MongoDB document Id to identify pizza to edit
//	 * @param updatePizza New Pizza object to replace the one currently under the Id
//	 * @return Saves updated pizza in the database
//	 */
//	@PutMapping
//	public Pizza updatePizza(@RequestParam ObjectId _id, @RequestBody Pizza updatePizza) {
//		updatePizza.set_id(_id);
//		return pizzaServ.save(updatePizza);
//	}
//	
//	// DELETE
//	/**
//	 * Delete method for removing a pizza from the database
//	 * @param _id the Id of the pizza to be deleted
//	 */
//	@DeleteMapping
//	public void deletePizza(@RequestParam ObjectId _id) {
//		Pizza pizzaToDelete = pizzaServ.findBy_id(_id);
//		pizzaServ.delete(pizzaToDelete);
//	}
//}

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
