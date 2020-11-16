package com.pizzeria.training.controllers;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.service.PizzaService;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

	@Autowired
	private PizzaService pizzaServ;
	
	public PizzaController() {
	}
	
	@Autowired
	public PizzaController(PizzaService pizzaServ) {
		super();
		this.pizzaServ = pizzaServ;
	}

	@GetMapping("/test")
	public @ResponseBody String getAllOrders(){
		
		return "Pizza Endpoint works";
	}
	 
	@RequestMapping(value = "/getPizza", method = RequestMethod.GET)
	public List<Pizza> getAllCustomer() {
	  return pizzaServ.findAll();
	}
	
	@PostMapping("/addPizza")
	public Pizza newCustomer(@RequestBody Pizza newPizza) {
	    return pizzaServ.save(newPizza);
	  }
	 
	//Filters by field, just type one field, and it should find the respective pizza
	@GetMapping("/byExample")
	public List<Pizza> getAllByExample(@RequestBody Pizza pizza){
		return pizzaServ.getAllByExample(pizza);
	}
	
	@GetMapping("/byLessThanCost")
	public List<Pizza> sortAllByCost(@RequestParam(name="cost") double cost){
		return pizzaServ.getAllByCostLessThan(cost);
	}
	
//	@GetMapping("/page")
//	public Map<String, Object> getAllPizzasInPage(@RequestParam(name="pageNo", defaultValue = "0") int pageNo,
//												  @RequestParam(name="pageSize", defaultValue = "2")int pageSize,
//												  @RequestParam(name="sortBy", defaultValue = "_id")String sortBy){
//		return pizzaServ.getAllPizzasInPage(pageNo,pageSize,sortBy);
//			
//	}
	
	@GetMapping("/page")
	public Map<String, Object> getAllPizzasInPage(@RequestParam(name="pageNo",defaultValue = "0") int pageNo,
												  @RequestParam(name="pageSize", defaultValue = "2") int pageSize,
												  @RequestParam(name="sortBy",defaultValue = "_id") String sortBy){
		return pizzaServ.getAllPizzasInPage(pageNo, pageSize, sortBy);
	}
	
	@GetMapping("/byType")
	public List<Pizza> getAllByType(@RequestParam(name="type") String type){
		return pizzaServ.getAllByType(type);
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
