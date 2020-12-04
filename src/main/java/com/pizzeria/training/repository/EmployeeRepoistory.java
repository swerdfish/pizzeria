package com.pizzeria.training.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.pizzeria.training.models.Customer;
import com.pizzeria.training.models.*;
public interface EmployeeRepoistory extends MongoRepository<Employee, ObjectId> {
	Employee findBy_id(ObjectId id);
}
