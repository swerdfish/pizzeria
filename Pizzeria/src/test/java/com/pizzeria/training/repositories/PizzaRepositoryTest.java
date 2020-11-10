package com.pizzeria.training.repositories;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.models.PizzaType;
import com.pizzeria.training.models.Size;
import com.pizzeria.training.models.Toppings;
import com.pizzeria.training.repository.PizzaRepository;

/**
 * Starts an ApplicationContext and an embedded MongoDB server for use with testing.
 * @author stephen.gruver
 *
 */
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class PizzaRepositoryTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	PizzaRepository pizzaRepo;
	
	Pizza testPizza = new Pizza(5.0f, PizzaType.CLASSIC, 
			new ArrayList<Toppings>(Arrays.asList(Toppings.ANCHOVY, Toppings.ARTICHOKE)),
			10.0d, Size.SMALL);
	
	@Test
	public void save() {
		Pizza saveResult = pizzaRepo.save(testPizza);
		
		assertNotNull(saveResult.get_id());
		assertEquals(saveResult.getCost(), testPizza.getCost());
		assertEquals(saveResult.getHeight(), testPizza.getHeight());
		assertEquals(saveResult.getSize(), testPizza.getSize());
		assertThat(saveResult.getToppings(), is(testPizza.getToppings()));
		assertEquals(saveResult.getType(), testPizza.getType());
	}
	
	@Test
	public void findAll() {
		pizzaRepo.save(testPizza);
		List<Pizza> findAllResult = pizzaRepo.findAll();
		
		assertThat(findAllResult, hasItem(testPizza));
	}
}
