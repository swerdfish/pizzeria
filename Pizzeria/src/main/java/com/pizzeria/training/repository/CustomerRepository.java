package com.pizzeria.training.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pizzeria.training.models.Customer;
/**
 * Spring Data Repository interface
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, ObjectId> {
	/**
	 * Returns a customer with the provided ID
	 * @param id The Id used to search
	 * @return The customer owning the ID
	 */
	Customer findBy_id(ObjectId id);
	/**
	 * Returns a list of customers whose addresses contain the provided city
	 * @param city The city used to search
	 * @return The list of customers in the city
	 */
	List<Customer> findByHomeAddressCity(String city);
}
