package com.pizzeria.training.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.pizzeria.training.models.Order;
import com.pizzeria.training.repository.OrdersRepository;

@Service
public class OrderService {

	private OrdersRepository orderRepo;
	private CustomerService custServ;
	
	public OrderService() {
	}
	
	@Autowired
	public OrderService(OrdersRepository orderRepo, CustomerService custServ) {
		super();
		this.orderRepo = orderRepo;
		this.custServ = custServ;
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

	public Order getOrderBy_id(ObjectId _id) {
		return orderRepo.findBy_id(_id);
	}
	
	public void delete(Order orderToDelete) {
		orderRepo.delete(orderToDelete);
	}

	public List<Order> getAllByExample(Order order) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();
		Example<Order> example = Example.of(order, matcher);
		return orderRepo.findAll(example);
	}
}
