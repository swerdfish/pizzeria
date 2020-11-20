
package com.pizzeria.training.models;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.testng.annotations.Test;

public class OrderModelTest {
	
	private Order testOrder;
	
	@Test
	public void noArgsConstructor() {
		//System.out.println("In @Test method: noArgsConstructor");
		testOrder = new Order();
		
		assertEquals(testOrder.getClass(), Order.class);
		assertNull(testOrder.get_id());
		assertNull(testOrder.getCustomerIdString());
		assertNull(testOrder.getCost());
		assertNull(testOrder.getFavorite());
		assertEquals(testOrder.getOrderStatus(), OrderStatus.IN_PROGRESS);
		assertNull(testOrder.getTip());
		assertTrue(testOrder.getPizzas().isEmpty());
	}
	
	@Test
	public void allArgsConstructor() {
		Pizza listItem = new Pizza();
		String testCustId = "test customerIdString";
		Double testCost = 1.0D;
		Double testTip = 2.0D;
		Boolean testFavorite = true;
		List<Pizza> pizzaList = new ArrayList<Pizza>(Arrays.asList(listItem));
		testOrder = new Order(pizzaList, testCustId, testCost, testTip, testFavorite);
		
		assertEquals(testOrder.getClass(), Order.class);
		assertNull(testOrder.get_id());
		assertThat(testOrder.getPizzas(), hasItems(listItem));
		assertEquals(testOrder.getCustomerIdString(), testCustId);
		assertEquals(testOrder.getCost(), testCost);
		assertEquals(testOrder.getTip(), testTip);
		assertEquals(testOrder.getFavorite(), testFavorite);
		assertEquals(testOrder.getOrderStatus(), OrderStatus.IN_PROGRESS);
	}
	
	@Test
	public void setters() {
		Pizza listItem = new Pizza();
		listItem.set_id(new ObjectId());
		ObjectId testId = new ObjectId();
		String testCustId = "test customerIdString";
		Double testCost = 1.0D;
		Double testTip = 2.0D;
		Boolean testFavorite = true;
		OrderStatus testStatus = OrderStatus.COMPLETED;
		List<Pizza> pizzaList = new ArrayList<Pizza>(Arrays.asList(listItem));
		testOrder = new Order();
		
		testOrder.set_id(testId);
		testOrder.setCost(testCost);
		testOrder.setCustomerIdString(testCustId);
		testOrder.setOrderStatus();
		testOrder.setPizzas(pizzaList);
		testOrder.setSetAsFavorite(testFavorite);
		testOrder.setTip(testTip);
		
		assertEquals(testOrder.getClass(), Order.class);
		assertEquals(testOrder.get_id(), testId);
		assertThat(testOrder.getPizzas(), hasItems(listItem));
		assertEquals(testOrder.getCustomerIdString(), testCustId);
		assertEquals(testOrder.getCost(), testCost);
		assertEquals(testOrder.getTip(), testTip);
		assertEquals(testOrder.getFavorite(), testFavorite);
		assertEquals(testOrder.getOrderStatus(), OrderStatus.COMPLETED);
	}
	
	@Test
	public void toStringTest() {
		testOrder = new Order();
		
		assertNotNull(testOrder.toString());
	}
}
