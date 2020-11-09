package com.pizzeria.training.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.training.models.Customer;
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository custRepo;
	
	public CustomerService() {
	}

	@Autowired
	public CustomerService(CustomerRepository custRepo) {
		super();
		this.custRepo = custRepo;
	}
	
	public List<Customer> findAll(){
		return custRepo.findAll();
	}
	
	public Customer save(Customer newCustomer) {
		return custRepo.save(newCustomer);
	}
	
	public List<Pizza> updateFavoriteOrder(ObjectId customerId, List<Pizza> newFavorite) throws Exception{
		Customer target;
		try {
			target = custRepo.findBy_id(customerId);
		} catch (Exception e) {
			throw new Exception("Target customer not found.");
		}
		
		target.setFavoriteOrder(newFavorite);
		target = custRepo.save(target);
		if (target.getFavoriteOrder().equals(newFavorite)) {
			return target.getFavoriteOrder();
		} else {
			throw new Exception("Target customer's favorite order did not properly update.");
		}
	}

	public List<Customer> getAllByCity(String city) {
		return custRepo.findByAddressCity(city);
	}
}
