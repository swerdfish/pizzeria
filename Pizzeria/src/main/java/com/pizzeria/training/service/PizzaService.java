package com.pizzeria.training.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

//	public List<Pizza> getAllByExample(Pizza pizza) {
//		ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreCase().withMatcher("height", GenericPropertyMatcher.of(StringMatcher.EXACT));
//		Example<Pizza> example = Example.of(pizza, matcher);
//		return pizzaRepo.findAll(example);
//	}
	
	public List<Pizza> getAllByExample(Pizza pizza){
		ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreCase().withMatcher("height", GenericPropertyMatcher.of(StringMatcher.EXACT));
		Example<Pizza> example = Example.of(pizza, matcher);
		return pizzaRepo.findAll(example);
	}

	public List<Pizza> getAllByCostLessThan(double cost) {
		return pizzaRepo.blahblah(cost);
	}

//	public Map<String, Object> getAllPizzasInPage(int pageNo, int pageSize, String sortBy) {
//		Map<String, Object> response = new HashMap<String, Object>();
//		Sort sort = Sort.by(sortBy);
//		Pageable page = PageRequest.of(pageNo, pageSize, sort);
//		Page<Pizza> pizzaPage = pizzaRepo.findAll(page);
//		response.put("data", pizzaPage.getContent());
//		response.put("Total number of pages", pizzaPage.getTotalPages());
//		response.put("Total number of elements", pizzaPage.getNumberOfElements());
//		response.put("Current page number", pizzaPage.getNumber());
//		return response;
//	}
	
	public Map<String, Object> getAllPizzasInPage(int pageNo, int pageSize, String sortBy){
		Map<String, Object> response = new HashMap<String, Object>();
		Sort sort = Sort.by(sortBy);
		Pageable page = PageRequest.of(pageNo, pageSize, sort);
		Page<Pizza> pizzaPage = pizzaRepo.findAll(page);
		response.put("data", pizzaPage.getContent());
		response.put("Total number of pages ", pizzaPage.getTotalPages());
		response.put("Total number of elements ", pizzaPage.getTotalElements());
		response.put("Current page number", pizzaPage.getNumber());
		return response;
	}

	public List<Pizza> getAllByType(String type) {
		return pizzaRepo.getAllByType(type);
	}

}
