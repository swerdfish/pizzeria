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

@Service
public class OrderService {

	private OrdersRepository orderRepo;
	private CustomerService custServ;
	private MongoTemplate mongoTemplate;
	
	public OrderService() {
	}
	
	@Autowired
	public OrderService(OrdersRepository orderRepo, CustomerService custServ, MongoTemplate mongoTemplate) {
		super();
		this.orderRepo = orderRepo;
		this.custServ = custServ;
		this.mongoTemplate = mongoTemplate;
	}
	
	public Order save(Order newOrder) {
		return orderRepo.save(newOrder);
	}

	public List<Order> findAll(){
		return orderRepo.findAll();
	}
	
	public Order getOrderBy_id(ObjectId _id) {
		return orderRepo.findBy_id(_id);
	}
	
	public List<Order> getOrdersByCust_Id(ObjectId cust_id){
		return orderRepo.findByCustomer_id(cust_id);
	}

	public List<Order> getAllByExample(Order order) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();
		Example<Order> example = Example.of(order, matcher);
		return orderRepo.findAll(example);
	}
	
	public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
		Query query = new Query();
		query.addCriteria(Criteria.where("orderStatus").is(orderStatus.toString()));
		List<Order> filteredOrders = mongoTemplate.find(query, Order.class);
		
		return filteredOrders;
	}

	public void delete(Order orderToDelete) {
		orderRepo.delete(orderToDelete);
	}

}
