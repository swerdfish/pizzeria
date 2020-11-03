package com.pizzeria.training.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.pizzeria.training.models.Order;

public interface OrdersRepository extends MongoRepository<Order, Integer> {

	Order findBy_id(ObjectId id);
}
