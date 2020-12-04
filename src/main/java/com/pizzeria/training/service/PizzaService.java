package com.pizzeria.training.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.repository.PizzaRepository;

@Service
public class PizzaService {

	private PizzaRepository pizzaRepo;
	
	public PizzaService() {
	}

	@Autowired
	public PizzaService(PizzaRepository pizzaRepo) {
		super();
		this.pizzaRepo = pizzaRepo;
	}
	
	public List<Pizza> findAll(){
		return pizzaRepo.findAll();
	}
	
	public Pizza save(Pizza newPizza) {
		return pizzaRepo.save(newPizza);
	}

	public List<Pizza> getAllByExample(Pizza pizza) {
		ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreCase().withMatcher("height", GenericPropertyMatcher.of(StringMatcher.EXACT));
		Example<Pizza> example = Example.of(pizza, matcher);
		return pizzaRepo.findAll(example);
	}

	public Pizza findBy_id(ObjectId _id) {
		return pizzaRepo.findBy_id(_id);
	}

	public void delete(Pizza pizzaToDelete) {
		pizzaRepo.delete(pizzaToDelete);
	}
}
