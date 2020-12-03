package com.pizzeria.training.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.pizzeria.training.models.Address;
import com.pizzeria.training.models.Customer;
import com.pizzeria.training.models.Order;
import com.pizzeria.training.models.OrderStatus;
import com.pizzeria.training.models.OrderType;
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.repository.OrdersRepository;

@DataMongoTest
public class OrdersRepositoryIntegrationTest extends AbstractTestNGSpringContextTests {
	
	@Autowired OrdersRepository orderRepo;

	ObjectId test_id = null;
	Customer testCustomer = null;
	ObjectId testPizzeria = null;
	List<Pizza> testPizzas = Arrays.asList(new Pizza(), new Pizza());
	double testCost = 10.0D;
	double testTip = 2.0D;
	OrderStatus testStatus = OrderStatus.DELIVERING;
	OrderType testType = OrderType.DELIVERY;
	Address testAddress = null;

	private Order testOrder = new Order(testCustomer, testPizzeria, testPizzas, testCost, testTip, testType, testAddress);
	
	@Test
	public void save() {
		Order savedOrder = orderRepo.save(testOrder);
		
		assertNotNull(savedOrder.get_id());
		assertEquals(savedOrder.getCustomer(), testCustomer);
		assertEquals(savedOrder.getPizzeriaId(), testPizzeria);
		assertThat(savedOrder.getPizzas(), is(testPizzas));
		assertEquals(savedOrder.getCost(), testCost);
		assertEquals(savedOrder.getTip(), testTip);
		assertEquals(savedOrder.getStatus(), OrderStatus.PENDING);
		assertEquals(savedOrder.getType(), testType);
		assertEquals(savedOrder.getDeliveryAddress(), testAddress);
		
		test_id = savedOrder.get_id();
	}
	
	@Test(dependsOnMethods = {"save"})
	public void findBy_Id() {
		Order foundOrder = orderRepo.findBy_id(test_id);
		
		assertNotNull(foundOrder);
		assertEquals(foundOrder.get_id(), test_id);
	}
	
	
	
}
