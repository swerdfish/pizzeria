package com.pizzeria.training.controllers;

import java.util.Collections;
import java.util.List;

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
import com.pizzeria.training.service.OrderService;
/**
 * REST Controller class for Order object to interact with mongoDB database
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/orders")
public class OrderController {
	/**
	 * Service to route requests to repository
	 */
	private OrderService orderServ;
	/** No argument constructor */
	public OrderController() {
	}
	/**
	 * Parameterized constructor
	 * @param orderServ Service to route requests
	 */
	@Autowired
	public OrderController(OrderService orderServ) {
		super();
		this.orderServ = orderServ;
	}
	/**
	 * Test endpoint
	 * @return  Message to indicate the Order endpoint is functional
	 */
	@GetMapping("/test")
	public String test(){
		return "Orders Endpoint works";
	}
	
	// CREATE
	/**
	 * Post method to add an order to the database
	 * @param newOrder new Order object to be added
	 * @return Saves new order to the database
	 */
	@PostMapping
	public Order newOrder(@RequestBody Order newOrder) {
		return orderServ.save(newOrder);
	}
	
	// READ
	/**
	 * Get method for all orders in the database
	 * @param _id Optional parameter to filter by order Id
	 * @return List of all orders in the database
	 */
	@GetMapping
	public List<Order> getAllOrders(@RequestParam(required=false) ObjectId _id) {
		if (_id!=null) return Collections.singletonList(orderServ.getOrderBy_id(_id));
		return orderServ.findAll();
	}

	@GetMapping(path="/{orderStatus}")
	public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable("orderStatus") String orderStatus) {
		try {
			return new ResponseEntity<>(orderServ.getOrdersByStatus(OrderStatus.valueOf(orderStatus)), HttpStatus.OK);			
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//GET ORDERS BY CUSTOMER ID
	@GetMapping(path="/?cust_id={cust_id}")
	public ResponseEntity<List<Order>> getCustomerByOrderId(@RequestParam ObjectId cust_id){
		if (cust_id==null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(orderServ.getOrdersByCustomerId(cust_id), HttpStatus.OK);
	}

	/**
	 * Get method for orders using an example order object
	 * @param order The order to be used as an example
	 * @return Orders matching the given example
	 */
	@PostMapping("/examples")
	public List<Order> getAllOrderByExample(@RequestBody Order order) {
		return orderServ.getAllByExample(order);
	}
	
	// UPDATE
	/**
	 * Put method for updating order information
	 * @param _id MongoDB document Id to identify order to edit
	 * @param updateOrder New order object to replace the one currently under the Id
	 * @return Saves updated order in the database
	 */
	@PutMapping
	public Order updateOrder(@RequestParam ObjectId _id, @RequestBody Order updateOrder) {
		updateOrder.set_id(_id);
		return orderServ.save(updateOrder);
	}

	// DELETE
	/**
	 * Delete method for removing an order form the database
	 * @param _id The Id of the order to be deleted
	 */
	@DeleteMapping
	public void deleteOrder(@RequestParam ObjectId _id) {
		Order orderToDelete = orderServ.getOrderBy_id(_id);
		orderServ.delete(orderToDelete);
	}
	
}
/*

{
	"customer":{
		"_id":"5fbc3c9d600ae701ef07d947"
	},
	"pizzeriaId":null,
	"pizzas":[
		{
			"type": "CLASSIC",
			"toppings": [
			"PEPPERONI", "SAUSAGE"
			],
			"cost": 10.0,
			"size": "LARGE"
		},
		{
			"type": "DEEP_DISH",
			"toppings": [
				"SPINACH", "CHEESE"
			],
			"cost": 20.0,
			"size": "SMALL"
		}
	],
	"cost":30.0,
	"tip":5.0,
	"status":"COOKING",
	"type":"PICKUP",
	"deliveryAddress":{
		"streetAddress":"1 delivery",
		"streetAddressLine2":"2 delivery",
		"city":"city",
		"state":"state",
		"postal":"postal"
	}
}

*/
