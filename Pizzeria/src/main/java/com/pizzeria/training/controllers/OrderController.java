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
import com.pizzeria.training.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private OrderService orderServ;
	
	public OrderController() {
	}

	@Autowired
	public OrderController(OrderService orderServ) {
		super();
		this.orderServ = orderServ;
	}

	@GetMapping("/test")
	public @ResponseBody String test(){
		
		return "Orders Endpoint works";
	}
	
	@RequestMapping(value = "/getOrders", method = RequestMethod.GET)
	public @ResponseBody List<Order> getAllOrders() {
	  return orderServ.findAll();
	}
	
	
	@PostMapping("/addOrder")
	public @ResponseBody Order newOrder(@RequestBody Order newOrder) {
		return orderServ.save(newOrder);
		
	}
	
	//Sample Input
//	{
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
//	"customerIdString":"example",
//	"setAsFavorite":false,
//	"cost" : 12.0,
//	"tip" : 0
//	}
	
}
