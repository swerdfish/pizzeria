package com.pizzeria.training.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PizzaModelJUnit4Test {

	Pizza testPizza;
	
	@BeforeClass
	public static void beforeClass() {
		//Code you would want to run before doing a single test, like resetting the database.
	}
	
	@Before
	public void resetPizza() {
		System.out.println("Executing before @Test Method");
		testPizza = null;
	}
	
	@Test
	public void noArgsConstructor() {
		System.out.println("In @Test method: noArgsConstructor");
		testPizza = new Pizza();
		
		assertEquals(testPizza.getClass(), Pizza.class);
		assertNull(testPizza.getType());
		assertNull(testPizza.getToppings());
		assertNull(testPizza.getSize());
		assertEquals(0.0f, testPizza.getHeight(), 0.0f);
		assertEquals(0.0d, testPizza.getCost(), 0.0d);
	}
	
	@Test
	public void allArgsConstructor() {
		System.out.println("In @Test method: allArgsConstructor");
		List<Toppings> toppingList = new ArrayList<Toppings>(Arrays.asList(Toppings.ANCHOVY, Toppings.BACON));
		testPizza = new Pizza(5.0f, PizzaType.CLASSIC, toppingList, 10.0d, Size.SMALL);
		
		assertEquals(testPizza.getClass(), Pizza.class);
		assertEquals(testPizza.getType(), PizzaType.CLASSIC);
		assertThat(testPizza.getToppings(), is(toppingList));	//Hamcrest library for collection evaluation
		assertEquals(testPizza.getSize(), Size.SMALL);
		assertEquals(testPizza.getHeight(), 5.0f, 0.0f);
		assertEquals(testPizza.getCost(), 10.0d, 0.0d);
	}
	
	@Test
	public void setters() {
		System.out.println("In @Test method: setters");
		List<Toppings> toppingList = new ArrayList<Toppings>(Arrays.asList(Toppings.ARTICHOKE, Toppings.BASIL));
		testPizza = new Pizza();
		
		testPizza.setCost(5.0d);
		testPizza.setHeight(10.0f);
		testPizza.setSize(Size.MEDIUM);
		testPizza.setToppings(toppingList);
		testPizza.setType(PizzaType.DEEP_DISH);
		
		assertEquals(testPizza.getClass(), Pizza.class);
		assertEquals(testPizza.getType(), PizzaType.DEEP_DISH);
		assertThat(testPizza.getToppings(), is(toppingList));	//Hamcrest library for collection evaluation
		assertEquals(testPizza.getSize(), Size.MEDIUM);
		assertEquals(testPizza.getHeight(), 10.0f, 0.0f);
		assertEquals(testPizza.getCost(), 5.0d, 0.0d);
	}
	
	@Test
	public void toStringTest() {
		System.out.println("In @Test method: toStringTest");
		List<Toppings> toppingList = new ArrayList<Toppings>(Arrays.asList(Toppings.BROCCOLI, Toppings.CARROT));
		testPizza = new Pizza(5.0f, PizzaType.CLASSIC, toppingList, 10.0d, Size.SMALL);
		
		String expected = "Pizza [height=" + 5.0f + ", type=" + PizzaType.CLASSIC + ", toppings=" + toppingList + 
				", cost=" + 10.0d + ", size=" + Size.SMALL + "]";
		
		assertEquals(testPizza.toString(), expected);
	}
	
	@Test
	public void equalsHashcode() {
		List<Toppings> toppingList = new ArrayList<Toppings>(Arrays.asList(Toppings.CHEESE, Toppings.GARLIC));
		testPizza = new Pizza(5.0f, PizzaType.CLASSIC, toppingList, 10.0d, Size.SMALL);
		Pizza otherPizza = new Pizza(5.0f, PizzaType.CLASSIC, toppingList, 10.0d, Size.SMALL);
		
		assertEquals(testPizza, otherPizza);
		
		otherPizza.setCost(2.0d);
		
		assertNotEquals(testPizza, otherPizza);
	}

}
