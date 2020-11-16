package com.pizzeria.training.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.models.PizzaType;
import com.pizzeria.training.models.Size;
import com.pizzeria.training.models.Toppings;
import com.pizzeria.training.repository.PizzaRepository;
import com.pizzeria.training.service.PizzaService;

public class PizzaServiceTest {
	
	static Pizza testPizza = 
			new Pizza(5.0f, PizzaType.CLASSIC, 
					new ArrayList<Toppings>(Arrays.asList(Toppings.ANCHOVY, Toppings.BACON)), 
					10.0d, Size.SMALL);
	
	@MockBean
	static PizzaRepository pizzaRepo;
	
	@Autowired
	static PizzaService pizzaServ;
	
	@BeforeClass
	public static void mockSetup() {
		List<Pizza> pizzaList = new ArrayList<Pizza>(Arrays.asList(testPizza));
		when(pizzaRepo.findAll()).thenReturn(pizzaList);
		when(pizzaRepo.save(testPizza)).thenReturn(testPizza);
	}
	
	@Test
	public void findAll() {
		List<Pizza> pizzaList = pizzaServ.findAll();
		
		MatcherAssert.assertThat(pizzaList, CoreMatchers.hasItem(testPizza));
		verify(pizzaRepo, times(1)).findAll();
	}
	
	@Test
	public void save() {
		Pizza savedPizza = pizzaServ.save(testPizza);
		
		verify(pizzaRepo, times(1)).save(testPizza);
	}
}
