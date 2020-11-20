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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	/**
	 * Patch method to update the status of an order to "complete"
	 * @param orderId Id of the order to mark as completed
	 * @return An updated order marked as complete
	 */
	@PatchMapping(path="/{orderId}")
	public ResponseEntity<String> completeOrder(@PathVariable("orderId") String orderId) {
		return orderServ.updateStatus(new ObjectId(orderId));
	}
	/**
	 * Get method for orders sorted by status
	 * @param orderStatus The status of the orders to be looked up
	 * @return A list of all orders with the given status
	 */
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
