package com.pizzeria.training.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.models.Toppings;
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
		List<Pizza> pizzas = pizzaRepo.findAll();
		System.out.println(pizzas.get(0).getToppings()==new HashSet<>(Arrays.asList(Toppings.BACON, Toppings.PINEAPPLE)));
		System.out.println(pizzas.get(0).getToppings().equals(new HashSet<>(Arrays.asList(Toppings.PINEAPPLE, Toppings.BACON))));
		return pizzaRepo.findAll();
	}
	
	public Pizza save(Pizza newPizza) {
		return pizzaRepo.save(newPizza);
	}

	public List<Pizza> getAllByExample(Pizza pizza) {
		System.out.println(pizza);
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withMatcher("height", GenericPropertyMatcher.of(StringMatcher.EXACT));
		// Filter toppings manually
		Set<Toppings> toppingsCopy = pizza.getToppings();//==null ? null : new HashSet<>(pizza.getToppings());
		pizza.setToppings(null);
		Example<Pizza> example = Example.of(pizza, matcher);
		List<Pizza> pizzas = pizzaRepo.findAll(example);
		if (toppingsCopy!=null) pizzas.removeIf(p -> !p.getToppings().equals(toppingsCopy));
		return pizzas;
	}

	public Pizza findBy_id(ObjectId _id) {
		return pizzaRepo.findBy_id(_id);
	}

	public void delete(Pizza pizzaToDelete) {
		pizzaRepo.delete(pizzaToDelete);
	}
}
