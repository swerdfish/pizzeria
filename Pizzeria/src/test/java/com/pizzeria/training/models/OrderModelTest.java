
package com.pizzeria.training.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.mockito.Mockito;
import org.testng.annotations.Test;

public class OrderModelTest {
	
	private Order testOrder;
	
	Customer testCustomer = Mockito.mock(Customer.class);
	ObjectId testPizzeria = null;
	List<Pizza> testPizzas = Arrays.asList(new Pizza(), new Pizza());
	Double testCost = 10.0D;
	Double testTip = 2.0D;
	OrderStatus testStatus = OrderStatus.DELIVERING;
	OrderType testType = OrderType.DELIVERY;
	Address testAddress = Mockito.mock(Address.class);
	
	@Test(groups = {"orders", "fast"})
	public void noArgsConstructor() {
		//System.out.println("In @Test method: noArgsConstructor");
		testOrder = new Order();
		
		assertEquals(testOrder.getClass(), Order.class);
		assertNull(testOrder.get_id());
		assertNull(testOrder.getCustomer());
		assertNull(testOrder.getPizzeriaId());
		assertNull(testOrder.getPizzas());
		assertNull(testOrder.getCost());
		assertNull(testOrder.getTip());
		assertNull(testOrder.getStatus());
		assertNull(testOrder.getType());
		assertNull(testOrder.getDeliveryAddress());
	}
	
	@Test(groups = {"orders", "fast"})
	public void allArgsConstructor() {
		testOrder = new Order(testCustomer, testPizzeria, testPizzas, testCost, testTip, testStatus, testType, testAddress);
		
		assertEquals(testOrder.getClass(), Order.class);
		assertNull(testOrder.get_id());
		assertEquals(testOrder.getCustomer(), testCustomer);
		assertEquals(testOrder.getPizzeriaId(), testPizzeria);
		assertThat(testOrder.getPizzas(), is(testPizzas));
		assertEquals(testOrder.getCost(), testCost);
		assertEquals(testOrder.getTip(), testTip);
		assertEquals(testOrder.getStatus(), testStatus);
		assertEquals(testOrder.getType(), testType);
		assertEquals(testOrder.getDeliveryAddress(), testAddress);
	}
	
	@Test(groups = {"orders", "fast"})
	public void setters() {
		ObjectId test_id = new ObjectId();
		testOrder = new Order();
		
		testOrder.set_id(test_id);
		testOrder.setCustomer(testCustomer);
		testOrder.setPizzeriaId(testPizzeria);
		testOrder.setPizzas(testPizzas);
		testOrder.setCost(testCost);
		testOrder.setTip(testTip);
		testOrder.setStatus(OrderStatus.DELIVERING);
		testOrder.setType(OrderType.DELIVERY);
		testOrder.setDeliveryAddress(testAddress);
		
		assertEquals(testOrder.getClass(), Order.class);
		assertEquals(testOrder.get_id(), test_id);
		assertEquals(testOrder.getCustomer(), testCustomer);
		assertEquals(testOrder.getPizzeriaId(), testPizzeria);
		assertThat(testOrder.getPizzas(), is(testPizzas));
		assertEquals(testOrder.getCost(), testCost);
		assertEquals(testOrder.getTip(), testTip);
		assertEquals(testOrder.getStatus(), OrderStatus.DELIVERING);
		assertEquals(testOrder.getType(), testType);
		assertEquals(testOrder.getDeliveryAddress(), testAddress);
	}
	
	@Test(groups = {"orders", "fast"})
	public void equalsHashCode() {
		Order o1 = new Order(testCustomer, testPizzeria, testPizzas, testCost, testTip, testStatus, testType, testAddress);
		Order o2 = new Order(testCustomer, testPizzeria, testPizzas, testCost, testTip, testStatus, testType, testAddress);
		assertTrue(o1.equals(o2));
		assertTrue(o1.hashCode() == o2.hashCode());
	}
	
	@Test(groups = {"orders", "fast"})
	public void toStringTest() {
		assertTrue(new Order().toString().contains("_id="));
	}
}
