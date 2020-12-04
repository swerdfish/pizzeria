package com.pizzeria.training.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pizzeria.training.models.Order;

@Repository
public interface OrdersRepository extends MongoRepository<Order, ObjectId> {

	Order findBy_id(ObjectId id);
}
