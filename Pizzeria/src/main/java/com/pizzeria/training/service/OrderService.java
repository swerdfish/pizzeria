package com.pizzeria.training.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pizzeria.training.models.Order;
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
	
	public List<Order> findAll(){
		return orderRepo.findAll();
	}
	
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
	
	public List<Order> getOrdersByStatus(String orderStatus) {
		Query query = new Query();
		query.addCriteria(Criteria.where("orderStatus").is(orderStatus));
		List<Order> filteredOrders = mongoTemplate.find(query, Order.class);
		
		return filteredOrders;
	}
	
}
