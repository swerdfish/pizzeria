package com.pizzeria.training.services;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.mockito.ArgumentMatchers;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pizzeria.training.models.Address;
import com.pizzeria.training.models.Customer;
import com.pizzeria.training.models.Order;
import com.pizzeria.training.models.OrderStatus;
import com.pizzeria.training.models.OrderType;
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.repository.OrdersRepository;
import com.pizzeria.training.service.OrderService;

public class OrderServiceUnitTest {

	private OrdersRepository orderRepo = mock(OrdersRepository.class);
	private MongoTemplate mongo = mock(MongoTemplate.class);

	private OrderService orderServ = new OrderService(orderRepo, mongo);

	private Customer mockCust = mock(Customer.class);
	private Pizza mockPizza = mock(Pizza.class);
	private Address mockAddress = mock(Address.class);
	private Order testOrder = spy(new Order(mockCust, null, new ArrayList<Pizza>(Arrays.asList(mockPizza)), 1.5D, 2.5D,
			OrderStatus.DELIVERING, OrderType.DELIVERY, mockAddress));
	private List<Order> testOrders = new ArrayList<>(Arrays.asList(testOrder));

	@Test(groups = { "orders", "create", "fast", "validinput" })
	public void saveValid() {
		when(orderRepo.save(testOrder)).thenReturn(testOrder);
		assertEquals(orderServ.save(testOrder), testOrder);
		verify(orderRepo, times(1)).save(testOrder);
	}

	@Test(groups = { "orders", "create", "fast", "invalidinput" }, expectedExceptions = IllegalArgumentException.class)
	public void saveThrowIfNullOrder() throws IllegalArgumentException {
		orderServ.save(null);
	}

	@Test(groups = { "orders", "create", "fast", "invalidinput" }, expectedExceptions = IllegalArgumentException.class)
	public void saveThrowIfEmptyPizzaList() throws IllegalArgumentException {
		when(testOrder.getPizzas()).thenReturn(Collections.<Pizza>emptyList());
		orderServ.save(testOrder);
	}

	@Test(groups = { "orders", "create", "fast", "invalidinput" }, expectedExceptions = IllegalArgumentException.class)
	public void saveThrowIfMissingType() throws IllegalArgumentException {
		when(testOrder.getType()).thenReturn(null);
		orderServ.save(testOrder);
	}

	@Test(groups = { "orders", "create", "fast", "invalidinput" }, expectedExceptions = IllegalArgumentException.class)
	public void saveThrowIfDeliveryAndMissingAddress() throws IllegalArgumentException {
		when(testOrder.getDeliveryAddress()).thenReturn(null);
		orderServ.save(testOrder);
	}

	@Test(groups = { "orders", "create", "fast", "invalidinput" }, expectedExceptions = IllegalArgumentException.class)
	public void saveThrowIfDeliveryAndMissingCustomer() throws IllegalArgumentException {
		when(testOrder.getCustomer()).thenReturn(null);
		orderServ.save(testOrder);
	}

	@Test(groups = { "orders", "create", "fast", "validinput" })
	public void saveFillInMissingFields() {
		testOrder.setCost(null);
		testOrder.setTip(null);
		testOrder.setStatus(null);
		when(mockPizza.getCost()).thenReturn(1.5D);

		orderServ.save(testOrder);

		verify(testOrder, times(1)).setCost(1.5D);
		verify(testOrder, times(1)).setTip(0.0D);
		verify(testOrder, times(1)).setStatus(OrderStatus.PENDING);
		verify(orderRepo, times(1)).save(testOrder);
	}

	@Test(groups = { "orders", "read", "fast" })
	public void findAll() {
		when(orderRepo.findAll()).thenReturn(testOrders);
		assertThat(orderServ.findAll(), hasItem(testOrder));
		verify(orderRepo, times(1)).findAll();
	}

	@Test(groups = { "orders", "read", "fast" })
	public void findById() {
		when(orderRepo.findBy_id(ArgumentMatchers.any(ObjectId.class))).thenReturn(testOrder);
		assertEquals(orderServ.getOrderBy_id(new ObjectId()), testOrder);
		verify(orderRepo, times(1)).findBy_id(ArgumentMatchers.any(ObjectId.class));
	}

	@Test(groups = { "orders", "read", "fast" })
	public void findByExample() {
		when(orderRepo.findAll(ArgumentMatchers.<Example<Order>>any())).thenReturn(testOrders);
		assertThat(orderServ.getAllByExample(testOrder), hasItem(testOrder));
		verify(orderRepo, times(1)).findAll(ArgumentMatchers.<Example<Order>>any());
	}

	@Test(enabled = false, groups = { "orders", "read", "fast" })
	public void findbyStatus() {
		when(mongo.find(ArgumentMatchers.any(Query.class), ArgumentMatchers.eq(Order.class))).thenReturn(testOrders);
		assertThat(orderServ.getOrdersByStatus(OrderStatus.PENDING), hasItem(testOrder));
		verify(mongo, times(1)).find(ArgumentMatchers.any(Query.class), ArgumentMatchers.eq(Order.class));
	}

	@Test(groups = { "orders", "read", "fast" })
	public void findByCustomerId() {
		when(orderRepo.findByCustomer_id(ArgumentMatchers.any(ObjectId.class))).thenReturn(testOrders);
		assertThat(orderServ.getOrdersByCustomerId(new ObjectId()), hasItem(testOrder));
		verify(orderRepo, times(1)).findByCustomer_id(ArgumentMatchers.any(ObjectId.class));
	}

	@Test(groups = { "orders", "update", "fast" }, enabled = false)
	public void upateOperations() {
	}

	@Test(groups = { "orders", "delete", "fast" })
	public void deleteOperations() {
		doNothing().when(orderRepo).delete(testOrder);
		orderServ.delete(testOrder);
		verify(orderRepo, times(1)).delete(testOrder);
	}

	@AfterMethod
	public void resetMocks() {
		reset(orderRepo);
		reset(mongo);
		reset(testOrder);
	}
}
