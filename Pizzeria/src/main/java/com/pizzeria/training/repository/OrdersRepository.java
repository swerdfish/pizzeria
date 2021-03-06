package com.pizzeria.training.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pizzeria.training.models.Order;
/**
 * Spring Data Repository interface
 */
@Repository
public interface OrdersRepository extends MongoRepository<Order, ObjectId> {
	/**
	 * Returns an order with the provided Id
	 * @param id The Id used to search
	 * @return The order with the given Id
	 */
	Order findBy_id(ObjectId id);
	
	@Query(value = "{ 'customer._id' : ?0 }")
	List<Order> findByCustomer_id(ObjectId cust_id);
}
