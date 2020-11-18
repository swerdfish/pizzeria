package com.pizzeria.training.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PizzaModelTestNGTest {
	
	Pizza testPizza;
	
	@BeforeClass
	public static void beforeClass() {
		//Code you would want to run before doing a single test, like resetting the database.
	}
	
	@BeforeMethod
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
		Set<Toppings> toppingSet = new HashSet<Toppings>(Arrays.asList(Toppings.ANCHOVY, Toppings.BACON));
		testPizza = new Pizza(5.0f, PizzaType.CLASSIC, toppingSet, 10.0d, Size.SMALL);
		
		assertEquals(testPizza.getClass(), Pizza.class);
		assertEquals(testPizza.getType(), PizzaType.CLASSIC);
		assertThat(testPizza.getToppings(), is(toppingSet));	//Hamcrest library for collection evaluation
		assertEquals(testPizza.getSize(), Size.SMALL);
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
		testPizza.setSize(Size.MEDIUM);
		testPizza.setToppings(toppingSet);
		testPizza.setType(PizzaType.DEEP_DISH);
		
		assertEquals(testPizza.getClass(), Pizza.class);
		assertEquals(testPizza.getType(), PizzaType.DEEP_DISH);
		assertThat(testPizza.getToppings(), is(toppingSet));	//Hamcrest library for collection evaluation
		assertEquals(testPizza.getSize(), Size.MEDIUM);
		assertEquals(testPizza.getHeight(), 10.0f, 0.0f);
		assertEquals(testPizza.getCost(), 5.0d, 0.0d);
	}
	
	@Test
	public void toStringTest() {
		System.out.println("In @Test method: toStringTest");
		Set<Toppings> toppingSet = new HashSet<Toppings>(Arrays.asList(Toppings.BROCCOLI, Toppings.CARROT));
		testPizza = new Pizza(5.0f, PizzaType.CLASSIC, toppingSet, 10.0d, Size.SMALL);
		
		String expected = "Pizza [height=" + 5.0f + ", type=" + PizzaType.CLASSIC + ", toppings=" + toppingSet + 
				", cost=" + 10.0d + ", size=" + Size.SMALL + "]";
		
		assertEquals(testPizza.toString(), expected);
	}
	
	@Test
	public void equalsHashcode() {
		System.out.println("In @Test method: equalsHashCode");
		Set<Toppings> toppingSet = new HashSet<Toppings>(Arrays.asList(Toppings.CHEESE, Toppings.GARLIC));
		testPizza = new Pizza(5.0f, PizzaType.CLASSIC, toppingSet, 10.0d, Size.SMALL);
		Pizza otherPizza = new Pizza(5.0f, PizzaType.CLASSIC, toppingSet, 10.0d, Size.SMALL);
		
		assertEquals(testPizza, otherPizza);
		
		otherPizza.setCost(2.0d);
		
		assertNotEquals(testPizza, otherPizza);
	}
}
