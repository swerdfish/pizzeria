package com.pizzeria.training.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
}
