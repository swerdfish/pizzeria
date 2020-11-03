package com.pizzeria.training.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.pizzeria.training.models.Pizza;

public interface PizzaRepository extends MongoRepository<Pizza, Float>{

	Pizza findBy_id(ObjectId id);
}
