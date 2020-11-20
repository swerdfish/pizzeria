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
/**
 * Service class for routing pizza-related requests
 */
@Service
public class PizzaService {
	/** Spring Data Repository for pizza objects */
	private PizzaRepository pizzaRepo;
	/**
	 * No-argument constructor
	 */
	public PizzaService() {
	}
	/**
	 * Parameterized constructor
	 * @param pizzaRepo The repository to be connected to
	 */
	@Autowired
	public PizzaService(PizzaRepository pizzaRepo) {
		super();
		this.pizzaRepo = pizzaRepo;
	}
	/**
	 * Returns all Pizzas in the database
	 * @return List of all Pizzas available
	 */
	public List<Pizza> findAll(){
		return pizzaRepo.findAll();
	}
	/**
	 * Creates and saves a new pizza in the database
	 * @param newPizza New pizza object to be added
	 * @return Saves the new pizza to the database
	 */
	public Pizza save(Pizza newPizza) {
		return pizzaRepo.save(newPizza);
	}
	/**
	 * Returns a list of all pizzas matching an example
	 * @param pizza The pizza to be used as an example
	 * @return List of all pizzas matching the given example
	 */
	public List<Pizza> getAllByExample(Pizza pizza) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withMatcher("height", GenericPropertyMatcher.of(StringMatcher.EXACT));
		Example<Pizza> example = Example.of(pizza, matcher);
		return pizzaRepo.findAll(example);
	}
	/**
	 * Returns the pizza with the given Id
	 * @param _id The Id of the pizza to be retrieved
	 * @return Pizza with the provided Id
	 */
	public Pizza findBy_id(ObjectId _id) {
		return pizzaRepo.findBy_id(_id);
	}
	/**
	 * Deletes a pizza from the database
	 * @param pizzaToDelete Pizza object to be deleted
	 */
	public void delete(Pizza pizzaToDelete) {
		pizzaRepo.delete(pizzaToDelete);
	}
}
