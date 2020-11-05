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

import com.pizzeria.training.models.Order;
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.repository.OrdersRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrdersRepository repository;

	@GetMapping("/test")
	public @ResponseBody String test(){
		
		return "Endpoint works";
	}
	
	@RequestMapping(value = "/getOrders", method = RequestMethod.GET)
	public List<Order> getAllOrders() {
	  return repository.findAll();
	}
	
	
	@PostMapping("/addOrder")
	  Order newOrder(@RequestBody Order newOrder) {
	    return repository.save(newOrder);
	  }
	
	//Sample Input
//	{
//	    "orderID": 123,
//	    "pizzas":[{
//	    "height": 13.0,
//	    "type": "CLASSIC",
//	    "toppings" : [
//	             "BACON",
//	             "PINEAPPLE"
//	    ],
//	    "cost" : 12,
//	    "size" : "SMALL"
//	}],
//	"date" : "2020-12-10",
//	"cost" : 12.0,
//	"tip" : 0
//	}
	
}
