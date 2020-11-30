package com.pizzeria.training.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
	public OrderService(OrdersRepository orderRepo, MongoTemplate mongoTemplate) {
		super();
		this.orderRepo = orderRepo;
		this.mongoTemplate = mongoTemplate;
	}
	
	/**
	 * Creates/updates and saves a new order
	 * @param newOrder The order to be added to the database
	 * @return Saves new order in the database
	 */
	public Order save(Order newOrder) {
		return orderRepo.save(newOrder);
	}
	
	/**
	 * Returns all orders in the database
	 * @return List of all orders in the database
	 */
	public List<Order> findAll(){
		return orderRepo.findAll();
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
	 * Returns orders matching an example given
	 * @param order The order to be used as an example
	 * @return List of orders matching the example given
	 */
	public List<Order> getAllByExample(Order order) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();
		Example<Order> example = Example.of(order, matcher);
		return orderRepo.findAll(example);
	}
	/*
		public List<Pizza> getAllByExample(Pizza pizza) {
			System.out.println(pizza);
			ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withMatcher("height", GenericPropertyMatcher.of(StringMatcher.EXACT));
			// Filter toppings manually
			Set<Toppings> toppingsCopy = pizza.getToppings();//==null ? null : new HashSet<>(pizza.getToppings());
			pizza.setToppings(null);
			Example<Pizza> example = Example.of(pizza, matcher);
			List<Pizza> pizzas = pizzaRepo.findAll(example);
			if (toppingsCopy!=null) pizzas.removeIf(p -> !p.getToppings().equals(toppingsCopy));
			return pizzas;
		}
	 */
	
	public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
		Query query = new Query();
		query.addCriteria(Criteria.where("orderStatus").is(orderStatus.toString()));
		List<Order> filteredOrders = mongoTemplate.find(query, Order.class);
		
		return filteredOrders;
	}
	
	public List<Order> getOrdersByCustomerId(ObjectId cust_id){
		return orderRepo.findByCustomer_id(cust_id);
	}

	/**
	 * Deletes an order from the database
	 * @param orderToDelete Order object to be removed
	 */
	public void delete(Order orderToDelete) {
		orderRepo.delete(orderToDelete);
	}
}
