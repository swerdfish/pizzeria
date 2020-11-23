package com.pizzeria.training.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.pizzeria.training.models.Order;
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.repository.OrdersRepository;

@DataMongoTest
public class OrdersRepositoryIntegrationTest extends AbstractTestNGSpringContextTests {
	
	@Autowired OrdersRepository orderRepo;

	private ObjectId orderId = null;
	private Pizza testPizza1 = new Pizza();
	private Pizza testPizza2 = new Pizza();
	private List<Pizza> testPizzas = Arrays.asList(testPizza1, testPizza2);
	private String testCustId = "test customer id";
	private Double testCost = 1.0D;
	private Double testTip = 1.0D;
	private Boolean testFavorite = true;

	private Order testOrder = new Order(testPizzas, testCustId, testCost, testTip, testFavorite);
	
	@Test
	public void save() {
		Order savedOrder = orderRepo.save(testOrder);
		
		assertNotNull(savedOrder.get_id());
		assertEquals(savedOrder.getCost(), testOrder.getCost());
		assertEquals(savedOrder.getCustomerIdString(), testOrder.getCustomerIdString());
		assertEquals(savedOrder.getFavorite(), testOrder.getFavorite());
		assertEquals(savedOrder.getOrderStatus(), testOrder.getOrderStatus());
		assertThat(savedOrder.getPizzas(), is(testPizzas));
		assertEquals(savedOrder.getTip(), testOrder.getTip());
		
		orderId = savedOrder.get_id();
	}
	
	@Test(dependsOnMethods = {"save"})
	public void findBy_Id() {
		Order foundOrder = orderRepo.findBy_id(orderId);
		
		assertNotNull(foundOrder);
		assertEquals(foundOrder.get_id(), orderId);
	}
	
}
