package com.pizzeria.training.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.bson.types.ObjectId;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@Autowired
	ObjectMapper objMap;
	
	@MockBean private PizzaService pizzaServ;
	
	private Pizza testPizza;
	
	@BeforeMethod
	private void mockSetup() {
		testPizza = new Pizza(5.0f, PizzaType.CLASSIC, 
							new HashSet<Toppings>(Arrays.asList(Toppings.ANCHOVY, Toppings.BACON)), 
							10.0d, Size.SMALL);
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
		ObjectId newId = new ObjectId();
		testPizza.set_id(newId);
		List<Pizza> pizzaList = new ArrayList<Pizza>(Arrays.asList(testPizza));
		when(pizzaServ.findAll()).thenReturn(pizzaList);
		
		mvc.perform(get("/pizzas"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.[0]._id.hexString", is(newId.toHexString())));
		
		verify(pizzaServ, times(1)).findAll();
	}
	
	@Test
	void newPizza() throws Exception {
		String bodyJSON = objMap.writeValueAsString(testPizza);
		ObjectId newId = new ObjectId();
		testPizza.set_id(newId);
		when(pizzaServ.save(ArgumentMatchers.any(Pizza.class))).thenReturn(testPizza);
		
		mvc.perform(post("/pizzas")
						.contentType(MediaType.APPLICATION_JSON)
						.content(bodyJSON)
					)
					.andExpect(status().isOk())
					.andExpect(jsonPath("$._id.hexString", is(newId.toHexString())));
		
		verify(pizzaServ).save(ArgumentMatchers.any());
		
		reset(pizzaServ);	//necessary to accurately verify in both newPizza() and update()
	}
	
	@Test
	void update() throws Exception {
		ObjectId newId = new ObjectId();
		String bodyJSON = objMap.writeValueAsString(testPizza);
		testPizza.set_id(newId);
		
		when(pizzaServ.save(ArgumentMatchers.any(Pizza.class))).thenReturn(testPizza);
		
		mvc.perform(put("/pizzas")
						.contentType(MediaType.APPLICATION_JSON)
						.param("_id", newId.toHexString())
						.content(bodyJSON)
					)
					.andExpect(status().isOk())
					.andExpect(jsonPath("$._id.hexString", is(newId.toHexString())));
		
		verify(pizzaServ, times(1)).save(ArgumentMatchers.any());
		
		assertEquals(newId, testPizza.get_id());
		
		reset(pizzaServ);	//necessary to accurately verify in both newPizza() and update()
	}
	
	@Test
	void delete() throws Exception {
		ObjectId newId = new ObjectId();
		when(pizzaServ.findBy_id(newId)).thenReturn(testPizza);
		doNothing().when(pizzaServ).delete(ArgumentMatchers.any());
		
		mvc.perform(MockMvcRequestBuilders.delete("/pizzas")
						.param("_id", newId.toHexString())
					)
					.andExpect(status().isOk())
					.andExpect(jsonPath("$").doesNotExist());
		
		verify(pizzaServ).findBy_id(newId);
		verify(pizzaServ).delete(testPizza);
	}
}
