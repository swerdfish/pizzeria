package com.pizzeria.training.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pizzeria.training.models.Pizza;
/**
 * Spring Data Repository interface
 */
@Repository
public interface PizzaRepository extends MongoRepository<Pizza, ObjectId>{
	/**
	 * Returns a pizza with the provided Id
	 * @param id The Id used to search
	 * @return The pizza with the given Id
	 */
	Pizza findBy_id(ObjectId id);
}
