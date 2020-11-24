package com.pizzeria.training.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pizzeria.training.models.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, ObjectId> {

	Customer findBy_id(ObjectId id);

	List<Customer> findByHomeAddressCity(String city);
}
