package com.pizzeria.training.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pizzeria.training.models.Pizza;

@Repository
public interface PizzaRepository extends MongoRepository<Pizza, Float>{

	Pizza findBy_id(ObjectId id);

	@Query(value ="{'cost': {$lte:?0} }")
	List<Pizza> blahblah(double cost);

	List<Pizza> getAllByType(String type);
}
