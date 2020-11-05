package com.pizzeria.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.training.models.Order;
import com.pizzeria.training.repository.OrdersRepository;

@Service
public class OrderService {

	private OrdersRepository orderRepo;
	
	public OrderService() {
	}
	
	@Autowired
	public OrderService(OrdersRepository orderRepo) {
		super();
		this.orderRepo = orderRepo;
	}
	
	public List<Order> findAll(){
		return orderRepo.findAll();
	}
	
	public Order save(Order newOrder) {
		return orderRepo.save(newOrder);
	}
}
