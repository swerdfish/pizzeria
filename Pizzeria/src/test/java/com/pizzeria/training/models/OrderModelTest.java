
package com.pizzeria.training.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
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
	double testCost = 10.0D;
	double testTip = 2.0D;
	OrderStatus testStatus = OrderStatus.DELIVERING;
	OrderType testType = OrderType.DELIVERY;
	Address testAddress = Mockito.mock(Address.class);
	
	@Test
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
		assertEquals(testOrder.getStatus(), OrderStatus.PENDING);
		assertNull(testOrder.getType());
		assertNull(testOrder.getDeliveryAddress());
	}
	
	@Test
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
	
	@Test
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
	
	@Test
	public void toStringTest() {
		testOrder = new Order();
		
		assertNotNull(testOrder);
	}
}
