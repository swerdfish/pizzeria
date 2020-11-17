package com.pizzeria.training.controllers;

import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.training.models.Order;
import com.pizzeria.training.service.OrderService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
	public String test(){
		return "Orders Endpoint works";
	}
	
	// CREATE
	
	@PostMapping
	public Order newOrder(@RequestBody Order newOrder) {
		return orderServ.save(newOrder);
	}
	
	// READ
	
	@GetMapping
	public List<Order> getAllOrders(@RequestParam(required=false) ObjectId _id) {
		if (_id!=null) return Collections.singletonList(orderServ.getOrderBy_id(_id));
		return orderServ.findAll();
	}
	
	@PostMapping("/examples")
	public List<Order> getAllOrderByExample(@RequestBody Order order) {
		return orderServ.getAllByExample(order);
	}
	
	// UPDATE
	
	@PutMapping
	public Order updateOrder(@RequestParam ObjectId _id, @RequestBody Order updateOrder) {
		updateOrder.set_id(_id);
		return orderServ.save(updateOrder);
	}
	
	@PutMapping("/completeOrder")
	public ResponseEntity<String> completeOrder(@RequestBody String orderId) {
		return orderServ.updateStatus(new ObjectId(orderId));
	}
	
	@GetMapping("/getOrdersByStatus/{orderStatus}")
	public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable String orderStatus) {
		
		if (!orderStatus.equals("Complete") || !orderStatus.equals("In Progress")) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		try {
			return new ResponseEntity<>(orderServ.getOrdersByStatus(orderStatus), HttpStatus.OK);			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// DELETE
	
	@DeleteMapping
	public void deleteOrder(@RequestParam ObjectId _id) {
		Order orderToDelete = orderServ.getOrderBy_id(_id);
		orderServ.delete(orderToDelete);
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
