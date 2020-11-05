package com.pizzeria.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.training.models.Customer;
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
}
