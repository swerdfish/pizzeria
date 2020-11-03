package com.pizzeria.training.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.pizzeria.training.models.Customer;

public interface CustomerRepository extends MongoRepository<Customer, Integer> {

	Customer findBy_id(ObjectId id);
}
