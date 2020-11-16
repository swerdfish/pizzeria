package com.pizzeria.training.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	public List<Customer> getAllByExample(Customer customer) {
		ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreCase().withMatcher("email", GenericPropertyMatcher.of(StringMatcher.EXACT));
		Example<Customer> example = Example.of(customer, matcher);
		return custRepo.findAll(example);
	}

	public Map<String, Object> getAllCustomersInPage(int pageNo, int pageSize, String sortBy) {
		Map<String, Object> response = new HashMap<String, Object>();
		Sort sort = Sort.by(sortBy);
		Pageable page = PageRequest.of(pageNo, pageSize, sort);
		Page<Customer> customerPage = custRepo.findAll(page);
		response.put("data", customerPage.getContent());
		response.put("Total number of pages ", customerPage.getTotalPages());
		response.put("Total number of elements ", customerPage.getTotalElements());
		response.put("Current page number", customerPage.getNumber());
		return response;
	}
}
