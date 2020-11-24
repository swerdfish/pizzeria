package com.pizzeria.training.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PizzaModelTest {
	
	private Pizza testPizza;
	
	PizzaType testType = PizzaType.CLASSIC;
	HashSet<Toppings> testToppings = new HashSet<Toppings>(Arrays.asList(Toppings.ANCHOVY, Toppings.BACON));
	Double testCost = 10.0D;
	PizzaSize testSize = PizzaSize.LARGE;
	
	@BeforeClass	//Code you would want to run before doing a single test, like resetting the database.
	public static void beforeClass() { /*...*/ }
	
	@BeforeMethod	//Code you would want to run before each method in this class
	public void resetPizza() { testPizza = null; }
	
	@Test
	public void noArgsConstructor() {
		//System.out.println("In @Test method: noArgsConstructor");
		testPizza = new Pizza();
		
		assertEquals(testPizza.getClass(), Pizza.class);
		assertNull(testPizza.getType());
		assertNull(testPizza.getToppings());
		assertNull(testPizza.getSize());
		assertNull(testPizza.getCost());
	}
	
	@Test
	public void allArgsConstructor() {
		testPizza = new Pizza(testType, testToppings, testCost, testSize);
		
		assertEquals(testPizza.getClass(), Pizza.class);
		assertEquals(testPizza.getType(), PizzaType.CLASSIC);
		assertThat(testPizza.getToppings(), is(testToppings));	//Hamcrest library for collection evaluation
		assertEquals(testPizza.getSize(), testSize);
		assertEquals(testPizza.getCost(), testCost, 0.0d);
	}
	
	@Test
	public void setters() {
		System.out.println("In @Test method: setters");
		testPizza = new Pizza();
		
		testPizza.setCost(testCost);
		testPizza.setSize(testSize);
		testPizza.setToppings(testToppings);
		testPizza.setType(testType);
		
		assertEquals(testPizza.getClass(), Pizza.class);
		assertEquals(testPizza.getType(), testType);
		assertThat(testPizza.getToppings(), is(testToppings));	//Hamcrest library for collection evaluation
		assertEquals(testPizza.getSize(), testSize);
		assertEquals(testPizza.getCost(), testCost, 0.0d);
	}
}
