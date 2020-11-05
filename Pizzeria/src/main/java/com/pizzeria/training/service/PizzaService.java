package com.pizzeria.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
