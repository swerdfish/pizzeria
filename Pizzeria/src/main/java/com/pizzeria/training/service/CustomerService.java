package com.pizzeria.training.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.pizzeria.training.models.Customer;
import com.pizzeria.training.models.Order;
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.repository.CustomerRepository;
/**
 * Service class for routing customer-related requests
 */
@Service
public class CustomerService {
	/** Spring Data Repository for customer objects */
	private CustomerRepository custRepo;

	/**
	 * No-argument constructor
	 */
	public CustomerService() {
	}
	/**
	 * Parameterized constructor
	 * @param custRepo The repository to be connected to
	 */
	@Autowired
	public CustomerService(CustomerRepository custRepo) {
		super();
		this.custRepo = custRepo;
	}
	
	/**
	 * Creates/updates and saves a new customer
	 * @param newCustomer The customer to be added to the database
	 * @return Saves new customer in the database
	 */
	public Customer save(Customer newCustomer) throws IllegalArgumentException {
		if (newCustomer == null) throw new IllegalArgumentException("Empty Customer document");
		if (newCustomer.getEmail() == null) throw new IllegalArgumentException("Customer email not provided");
		if (newCustomer.getPassword() == null) throw new IllegalArgumentException("Customer password not provided");
		if (newCustomer.getFirstName() == null) throw new IllegalArgumentException("Customer first name not provided");
		if (newCustomer.getLastName() == null) throw new IllegalArgumentException("Customer last name not provided");
		if (newCustomer.getPhoneNum() == null) throw new IllegalArgumentException("Customer phone number not provided");
		if (newCustomer.getFavoriteOrder() == null) newCustomer.setFavoriteOrder(Collections.<Pizza>emptyList());
		
		return custRepo.save(newCustomer);
	}

	/**
	 * Returns all customers in the database
	 * @return List of all customers in the database
	 */
	public List<Customer> findAll(){
		return custRepo.findAll();
	}
	
	/**
	 * Returns the customer with the given ID
	 * @param _id The id The ID to be looked up
	 * @return The customer with the given ID
	 */
	public Customer getCustomerBy_id(ObjectId _id) {
		return custRepo.findBy_id(_id);
	}
	
	/**
	 * Returns customers matching the example provided
	 * @param customer The customer object to be used as an example
	 * @return List of customers matching the example
	 */
	public List<Customer> findAllByExample(Customer customer) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();
		Example<Customer> example = Example.of(customer, matcher);
		return custRepo.findAll(example);
	}
	
	/**
	 * Returns a list of customers in the provided city
	 * @param city The city to be looked up
	 * @return The list of customers within the city
	 */
	public List<Customer> getAllByCity(String city) {
		return custRepo.findByHomeAddressCity(city);
	}

	/**
	 * Updates a customer's favorite order
	 * @param customerId The Id of the customer whose favorite order will be changed
	 * @param newFavorite The new order to be saved as a favorite
	 * @return saves the new favorite order
	 * @throws Exception Exception thrown if an update fails
	 */
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
	
	/**
	 * removes a customer from the database
	 * @param customer The customer object to be removed
	 */
	public void delete(Customer customer) {
		custRepo.delete(customer);
	}
}
