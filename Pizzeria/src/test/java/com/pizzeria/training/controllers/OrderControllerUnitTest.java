package com.pizzeria.training.controllers;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.mockito.ArgumentMatchers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pizzeria.training.models.Order;
import com.pizzeria.training.service.OrderService;

public class OrderControllerUnitTest {

	private OrderService orderServ = mock(OrderService.class);
	private OrderController orderCont = new OrderController(orderServ);
	
	private Order testOrder = mock(Order.class);
	private List<Order> testOrders = new ArrayList<>(Arrays.asList(testOrder));
	
	@Test(groups = {"orders", "utility", "fast"})
	public void testEndpoint() {
		assertEquals(orderCont.test(), new ResponseEntity<String>("Orders Endpoint works", HttpStatus.OK));
	}
	
	@Test(groups = {"orders", "create", "validinput", "fast"})
	public void createNewValid() {
		when(orderServ.save(testOrder)).thenReturn(testOrder);
		assertEquals(orderCont.newOrder(testOrder), new ResponseEntity<Order>(testOrder, HttpStatus.CREATED));
		verify(orderServ, times(1)).save(testOrder);
	}
	
	@Test(groups = {"orders", "create", "invalidinput", "fast"}, expectedExceptions = IllegalArgumentException.class)
	public void createNewInvalidOrder() {
		when(orderServ.save(testOrder)).thenThrow(IllegalArgumentException.class);
		orderCont.newOrder(testOrder);
	}
	
	@Test(groups = {"orders", "read", "fast"})
	public void getAllOrders() {
		when(orderServ.findAll()).thenReturn(testOrders);
		assertThat(orderCont.getOrders(null, null).getBody(), hasItem(testOrder));
		verify(orderServ, times(1)).findAll();
	}
	
	@Test(groups = {"orders", "read", "fast"})
	public void getOrderById() {
		when(orderServ.getOrderBy_id(ArgumentMatchers.<ObjectId>any())).thenReturn(testOrder);
		assertThat(orderCont.getOrders(new ObjectId(), null).getBody(), hasItem(testOrder));
		verify(orderServ, times(1)).getOrderBy_id(ArgumentMatchers.<ObjectId>any());
	}
	
	@Test(groups = {"orders", "read", "fast"})
	public void getOrdersByCustomerId() {
		when(orderServ.getOrdersByCustomerId(ArgumentMatchers.<ObjectId>any())).thenReturn(testOrders);
		assertThat(orderCont.getOrders(null, new ObjectId()).getBody(), hasItem(testOrder));
		verify(orderServ, times(1)).getOrdersByCustomerId(ArgumentMatchers.<ObjectId>any());
	}
	
	@Test(groups = {"orders", "read", "fast"})
	public void getAllOrdersByExample() {
		when(orderServ.getAllByExample(ArgumentMatchers.<Order>any())).thenReturn(testOrders);
		assertThat(orderCont.getAllOrdersByExample(testOrder).getBody(), hasItem(testOrder));
		verify(orderServ, times(1)).getAllByExample(ArgumentMatchers.<Order>any());
	}
	
	@Test(groups = {"orders", "update", "fast"})
	public void updateOrderValid() {
		when(orderServ.save(testOrder)).thenReturn(testOrder);
		assertEquals(orderCont.updateOrder(new ObjectId(), testOrder), new ResponseEntity<Order>(testOrder, HttpStatus.OK));
		verify(orderServ, times(1)).save(testOrder);
		verify(testOrder, times(1)).set_id(ArgumentMatchers.<ObjectId>any());
	}
	
	@Test(groups = {"orders", "update", "invalidinput", "fast"}, expectedExceptions = IllegalArgumentException.class)
	public void updateOrderInvalid() {
		when(orderServ.save(ArgumentMatchers.<Order>any())).thenThrow(IllegalArgumentException.class);
		orderCont.updateOrder(new ObjectId(), testOrder);
		verify(orderServ, times(1)).save(ArgumentMatchers.any());
		verify(testOrder, times(1)).set_id(ArgumentMatchers.<ObjectId>any());
	}
	
	@Test(groups = {"orders", "delete", "fast"})
	public void deleteOrder() {
		when(orderServ.getOrderBy_id(ArgumentMatchers.<ObjectId>any())).thenReturn(testOrder);
		doNothing().when(orderServ).delete(ArgumentMatchers.any());
		orderCont.deleteOrder(new ObjectId());
		verify(orderServ, times(1)).getOrderBy_id(ArgumentMatchers.<ObjectId>any());
		verify(orderServ, times(1)).delete(ArgumentMatchers.<Order>any());
	}
	
	@AfterMethod
	public void resetMocks() {
		reset(orderServ);
		reset(testOrder);
	}
}
