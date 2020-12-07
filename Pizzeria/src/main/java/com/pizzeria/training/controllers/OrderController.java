package com.pizzeria.training.controllers;

import java.util.Collections;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.training.models.Customer;
import com.pizzeria.training.models.Order;
import com.pizzeria.training.models.OrderStatus;
import com.pizzeria.training.service.CustomerService;
import com.pizzeria.training.service.OrderService;

/**
 * REST Controller class for Order object to interact with mongoDB database
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/orders")
public class OrderController {
	/**
	 * Service to route requests to order repository
	 */
	private OrderService orderServ;
	/**
	 * Service to route requests to customer repository
	 */
	private CustomerService custServ;

	/** No argument constructor */
	public OrderController() {
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param orderServ Service to route requests
	 */
	@Autowired
	public OrderController(OrderService orderServ, CustomerService custServ) {
		super();
		this.orderServ = orderServ;
		this.custServ = custServ;
	}

	/**
	 * Test endpoint
	 * 
	 * @return Message to indicate the Order endpoint is functional
	 */
	@GetMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>("Orders Endpoint works", HttpStatus.OK);
	}

	// CREATE
	/**
	 * Post method to add an order to the database
	 * 
	 * @param newOrder new Order object to be added
	 * @return Saves new order to the database
	 */
	@PostMapping
	public ResponseEntity<Order> newOrder(@RequestBody Order newOrder) {
//		try {
		return new ResponseEntity<Order>(orderServ.save(newOrder), HttpStatus.CREATED);
//		} catch (IllegalArgumentException e) {
//			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
	}

	// READ
	/**
	 * Get method for all orders in the database
	 * 
	 * @param _id Optional parameter to filter by order Id
	 * @param _id Optional parameter to filter by customer id
	 * @return List of all orders in the database
	 */
	@GetMapping
	public ResponseEntity<List<Order>> getOrders(@RequestParam(required = false) ObjectId _id,
			@RequestParam(required = false) ObjectId cust_id, @RequestParam(required = false) String email) throws AccountNotFoundException {
		if (_id != null)
			return new ResponseEntity<>(Collections.singletonList(orderServ.getOrderBy_id(_id)), HttpStatus.OK);
		if (cust_id != null)
			return new ResponseEntity<>(orderServ.getOrdersByCustomerId(cust_id), HttpStatus.OK);
		if (email != null) {
			Customer target = new Customer();
			target.setEmail(email);
			List<Customer> custsByEmail = custServ.findAllByExample(target);
			if (custsByEmail.isEmpty()) throw new AccountNotFoundException("No customer found for the given email");
			target = custServ.findAllByExample(target).get(0);
			return new ResponseEntity<List<Order>>(orderServ.getOrdersByCustomerId(target.get_id()), HttpStatus.OK);
		}
		return new ResponseEntity<List<Order>>(orderServ.findAll(), HttpStatus.OK);
	}

	/**
	 * @deprecated Functionality achieved by getAllOrdersByExample() by initializing
	 *             only the status field
	 * @param orderStatus
	 * @return
	 */
	@Deprecated
	@GetMapping(path = "/{orderStatus}")
	public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable("orderStatus") String orderStatus) {
		try {
			return new ResponseEntity<>(orderServ.getOrdersByStatus(OrderStatus.valueOf(orderStatus)), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Get method for orders using an example order object
	 * 
	 * @param order The order to be used as an example
	 * @return Orders matching the given example
	 */
	@PostMapping("/examples")
	public ResponseEntity<List<Order>> getAllOrdersByExample(@RequestBody Order order) {
		return new ResponseEntity<List<Order>>(orderServ.getAllByExample(order), HttpStatus.OK);
	}

	// UPDATE
	/**
	 * Put method for updating order information
	 * 
	 * @param _id         MongoDB document Id to identify order to edit
	 * @param updateOrder New order object to replace the one currently under the Id
	 * @return Saves updated order in the database
	 */
	@PutMapping
	public ResponseEntity<? extends Object> updateOrder(@RequestParam ObjectId _id, @RequestBody Order updateOrder)
			throws IllegalArgumentException {
		updateOrder.set_id(_id);
		return new ResponseEntity<Order>(orderServ.save(updateOrder), HttpStatus.OK);
	}

	// DELETE
	/**
	 * Delete method for removing an order form the database
	 * 
	 * @param _id The Id of the order to be deleted
	 */
	@DeleteMapping
	public void deleteOrder(@RequestParam ObjectId _id) {
		Order orderToDelete = orderServ.getOrderBy_id(_id);
		orderServ.delete(orderToDelete);
	}

}
/*
 * 
 * { "customer":{ "_id":"5fbc3c9d600ae701ef07d947" }, "pizzeriaId":null,
 * "pizzas":[ { "type": "CLASSIC", "toppings": [ "PEPPERONI", "SAUSAGE" ],
 * "cost": 10.0, "size": "LARGE" }, { "type": "DEEP_DISH", "toppings": [
 * "SPINACH", "CHEESE" ], "cost": 20.0, "size": "SMALL" } ], "cost":30.0,
 * "tip":5.0, "status":"COOKING", "type":"PICKUP", "deliveryAddress":{
 * "streetAddress":"1 delivery", "streetAddressLine2":"2 delivery",
 * "city":"city", "state":"state", "postal":"postal" } }
 * 
 */
