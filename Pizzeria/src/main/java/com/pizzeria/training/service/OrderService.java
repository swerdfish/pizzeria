package com.pizzeria.training.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pizzeria.training.models.Order;
import com.pizzeria.training.models.OrderStatus;
import com.pizzeria.training.repository.OrdersRepository;
/**
 * Service class for routing order-related requests
 */
@Service
public class OrderService {
	/** Spring Data Repository for order objects */
	private OrdersRepository orderRepo;
	/** Customer service to manipulate cutomer objects*/
	private CustomerService custServ;
	/** MongoTemplate configuration */
	private MongoTemplate mongoTemplate;
	/**No argument constructor*/
	public OrderService() {
	}
	/**
	 * Parameterized constructor
	 * @param orderRepo The repository to be connected to
	 * @param custServ Service for customer actions
	 * @param mongoTemplate Database template
	 */
	@Autowired
	public OrderService(OrdersRepository orderRepo, CustomerService custServ, MongoTemplate mongoTemplate) {
		super();
		this.orderRepo = orderRepo;
		this.custServ = custServ;
		this.mongoTemplate = mongoTemplate;
	}
	/**
	 * Returns all orders in the database
	 * @return List of all orders in the database
	 */
	public List<Order> findAll(){
		return orderRepo.findAll();
	}
	/**
	 * Creates/updates and saves a new order
	 * @param newOrder The order to be added to the database
	 * @return Saves new order in the database
	 */
	public Order save(Order newOrder) {
		if (newOrder.getFavorite()) {
			if (newOrder.getCustomerIdString() != null) {
				try {
					custServ.updateFavoriteOrder(new ObjectId(newOrder.getCustomerIdString()), newOrder.getPizzas());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Customer info not included. Customer favorite not updated.");
			}
		}
		return orderRepo.save(newOrder);
	}
	/**
	 * Updates order status to "complete"
	 * @param orderId Id of the order to mark as completed
	 * @return Message stating the update was successful
	 */
	public ResponseEntity<String> updateStatus(ObjectId orderId) {
		try {
			Order order = orderRepo.findBy_id(orderId);
			if (order == null) {
				return new ResponseEntity<>("Invalid Order Id!", HttpStatus.BAD_REQUEST);
			}
			order.setOrderStatus();
			orderRepo.save(order);			
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Successfully updated the order status!", HttpStatus.OK);
	}
	/**
	 * Returns orders based on status
	 * @param orderStatus Status used to search for orders
	 * @return A List of orders with the given status
	 */
	public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
		Query query = new Query();
		query.addCriteria(Criteria.where("orderStatus").is(orderStatus.toString()));
		List<Order> filteredOrders = mongoTemplate.find(query, Order.class);
		
		return filteredOrders;
	}
	/**
	 * Returns an order based on its Id
	 * @param _id Id used to identify the order
	 * @return Order with the given Id
	 */
	public Order getOrderBy_id(ObjectId _id) {
		return orderRepo.findBy_id(_id);
	}
	/**
	 * Deletes an order from the database
	 * @param orderToDelete Order object to be removed
	 */
	public void delete(Order orderToDelete) {
		orderRepo.delete(orderToDelete);
	}
	/**
	 * Returns orders matching an example given
	 * @param order The order to be used as an example
	 * @return List of orders matching the example given
	 */
	public List<Order> getAllByExample(Order order) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();
		Example<Order> example = Example.of(order, matcher);
		return orderRepo.findAll(example);
	}
}
