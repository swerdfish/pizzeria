package com.pizzeria.training.services;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.models.PizzaType;
import com.pizzeria.training.models.Size;
import com.pizzeria.training.models.Toppings;
import com.pizzeria.training.repository.PizzaRepository;
import com.pizzeria.training.service.PizzaService;

@TestExecutionListeners(MockitoTestExecutionListener.class)	//include this line or else mockbeans will be null in test class
public class PizzaServiceTest extends AbstractTestNGSpringContextTests{
	
	@InjectMocks
	PizzaService pizzaServ;

	@Mock
	PizzaRepository pizzaRepo;
	
	Pizza testPizza = 
			new Pizza(5.0f, PizzaType.CLASSIC, 
					new HashSet<Toppings>(Arrays.asList(Toppings.ANCHOVY, Toppings.BACON)), 
					10.0d, Size.SMALL);
	
	@BeforeMethod
	public void mockSetup() {
		List<Pizza> pizzaList = new ArrayList<Pizza>(Arrays.asList(testPizza));
		doReturn(pizzaList).when(pizzaRepo).findAll();
		doReturn(testPizza).when(pizzaRepo).save(testPizza);
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
