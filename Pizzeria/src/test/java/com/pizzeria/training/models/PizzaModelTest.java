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
		assertNull(testPizza.getHeight());
		assertNull(testPizza.getCost());
	}
	
	@Test
	public void allArgsConstructor() {
		Set<Toppings> toppingSet = new HashSet<Toppings>(Arrays.asList(Toppings.ANCHOVY, Toppings.BACON));
		testPizza = new Pizza(5.0f, PizzaType.CLASSIC, toppingSet, 10.0d, PizzaSize.SMALL);
		
		assertEquals(testPizza.getClass(), Pizza.class);
		assertEquals(testPizza.getType(), PizzaType.CLASSIC);
		assertThat(testPizza.getToppings(), is(toppingSet));	//Hamcrest library for collection evaluation
		assertEquals(testPizza.getSize(), PizzaSize.SMALL);
		assertEquals(testPizza.getHeight(), 5.0f, 0.0f);
		assertEquals(testPizza.getCost(), 10.0d, 0.0d);
	}
	
	@Test
	public void setters() {
		System.out.println("In @Test method: setters");
		Set<Toppings> toppingSet = new HashSet<Toppings>(Arrays.asList(Toppings.ARTICHOKE, Toppings.BASIL));
		testPizza = new Pizza();
		
		testPizza.setCost(5.0d);
		testPizza.setHeight(10.0f);
		testPizza.setSize(PizzaSize.MEDIUM);
		testPizza.setToppings(toppingSet);
		testPizza.setType(PizzaType.DEEP_DISH);
		
		assertEquals(testPizza.getClass(), Pizza.class);
		assertEquals(testPizza.getType(), PizzaType.DEEP_DISH);
		assertThat(testPizza.getToppings(), is(toppingSet));	//Hamcrest library for collection evaluation
		assertEquals(testPizza.getSize(), PizzaSize.MEDIUM);
		assertEquals(testPizza.getHeight(), 10.0f, 0.0f);
		assertEquals(testPizza.getCost(), 5.0d, 0.0d);
	}
	
	@Test
	public void toStringTest() {
		System.out.println("In @Test method: toStringTest");
		Set<Toppings> toppingSet = new HashSet<Toppings>(Arrays.asList(Toppings.BROCCOLI, Toppings.CARROT));
		testPizza = new Pizza(5.0f, PizzaType.CLASSIC, toppingSet, 10.0d, PizzaSize.SMALL);
		
		String expected = "Pizza [height=" + 5.0f + ", type=" + PizzaType.CLASSIC + ", toppings=" + toppingSet + 
				", cost=" + 10.0d + ", size=" + PizzaSize.SMALL + "]";
		
		assertEquals(testPizza.toString(), expected);
	}
	
	@Test
	public void equalsHashcode() {
		System.out.println("In @Test method: equalsHashCode");
		Set<Toppings> toppingSet = new HashSet<Toppings>(Arrays.asList(Toppings.CHEESE, Toppings.GARLIC));
		testPizza = new Pizza(5.0f, PizzaType.CLASSIC, toppingSet, 10.0d, PizzaSize.SMALL);
		Pizza otherPizza = new Pizza(5.0f, PizzaType.CLASSIC, toppingSet, 10.0d, PizzaSize.SMALL);
		
		assertEquals(testPizza, otherPizza);
		
		otherPizza.setCost(2.0d);
		
		assertNotEquals(testPizza, otherPizza);
	}
}
