package com.pizzeria.training.controllers;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.models.PizzaType;
import com.pizzeria.training.models.Size;
import com.pizzeria.training.models.Toppings;
import com.pizzeria.training.service.PizzaService;

@TestExecutionListeners(MockitoTestExecutionListener.class)	//include this line or else mockbeans will be null in test class
@WebMvcTest(PizzaController.class)
public class PizzaControllerIntegrationTest extends AbstractTestNGSpringContextTests /*extend this to start Spring context for test*/ {
	
	@Autowired 
	private MockMvc mvc;
	
	@MockBean
	private PizzaService pizzaServ;
	
	private Pizza testPizza = 
			new Pizza(5.0f, PizzaType.CLASSIC, 
					new HashSet<Toppings>(Arrays.asList(Toppings.ANCHOVY, Toppings.BACON)), 
					10.0d, Size.SMALL);
	
	@BeforeMethod
	private void mockSetup() {
		List<Pizza> pizzaList = new ArrayList<Pizza>(Arrays.asList(testPizza));
		doReturn(pizzaList).when(pizzaServ).findAll();
	}
	
	@Test
    void getTest() throws Exception {
		if (mvc == null) {
			System.out.println("mvc not injected");
			fail();
		}
        mvc.perform(get("/pizzas/test")).andExpect(status().isOk())
        								.andExpect(content().string("Pizza Endpoint works"));
    }
	
	@Test
	void getAll() throws Exception {
		List<Pizza> pizzaList = new ArrayList<Pizza>(Arrays.asList(testPizza));
		when(pizzaServ.findAll()).thenReturn(pizzaList);
		mvc.perform(get("/pizzas")).andExpect(status().isOk())
										.andExpect(jsonPath("$.[0].height", is(5.0)))
										.andExpect(jsonPath("$.[0].type", is("CLASSIC")))
										.andExpect(jsonPath("$.[0].cost", is(10.0)))
										.andExpect(jsonPath("$.[0].size", is("SMALL")))
										.andExpect(jsonPath("$.[0].toppings", hasItems("ANCHOVY", "BACON")));
	}
}
