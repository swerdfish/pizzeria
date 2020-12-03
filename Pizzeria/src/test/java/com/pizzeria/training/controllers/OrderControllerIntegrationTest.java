package com.pizzeria.training.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzeria.training.exception.ControllerAdvisor;
import com.pizzeria.training.models.Address;
import com.pizzeria.training.models.Customer;
import com.pizzeria.training.models.Order;
import com.pizzeria.training.models.OrderType;
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.service.OrderService;
import com.pizzeria.training.util.serializers.ObjectIdSerializer;

@WebMvcTest
@ContextConfiguration(classes = { OrderController.class, ObjectIdSerializer.class, ControllerAdvisor.class })
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
public class OrderControllerIntegrationTest extends AbstractTestNGSpringContextTests {

	@MockBean
	private OrderService orderServ;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objMap;

	private Order testOrder = new Order(new Customer(), null, new ArrayList<Pizza>(Arrays.asList(new Pizza())), 1.5D,
			2.5D, OrderType.DELIVERY, new Address());
	private List<Order> testOrders = new ArrayList<>(Arrays.asList(testOrder));

	@Test(enabled = false)
	public void contextLoads() {
	}

	@Test(enabled = false)
	public void testEndpoint() throws Exception {
		mvc.perform(get("/orders/test")).andExpect(status().isOk());
	}

	@Test(groups = { "orders", "create", "validinput", "slow" })
	public void createNewValid() throws Exception {
		testOrder.set_id(new ObjectId());
		when(orderServ.save(ArgumentMatchers.<Order>any())).thenReturn(testOrder);
		mvc.perform(
				post("/orders").contentType(MediaType.APPLICATION_JSON).content(objMap.writeValueAsString(testOrder)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("_id.timestamp", is(testOrder.get_id().getTimestamp())));
		verify(orderServ, times(1)).save(ArgumentMatchers.<Order>any());
	}

	@Test(groups = { "orders", "create", "invalidinput", "slow" })
	public void createNewInvalidOrder() throws Exception {
		when(orderServ.save(ArgumentMatchers.<Order>any())).thenThrow(IllegalArgumentException.class);
		mvc.perform(
				post("/orders").contentType(MediaType.APPLICATION_JSON).content(objMap.writeValueAsString(testOrder)))
				.andExpect(status().isBadRequest())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
	}

	@Test(groups = { "orders", "read", "slow" })
	public void getAllOrders() throws Exception {
		testOrder.set_id(new ObjectId());
		when(orderServ.findAll()).thenReturn(testOrders);
		mvc.perform(get("/orders")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]._id.hexString", is(testOrder.get_id().toHexString())));

	}

	@Test(groups = { "orders", "read", "slow" })
	public void getOrderById() throws Exception {
		testOrder.set_id(new ObjectId());
		when(orderServ.getOrderBy_id(ArgumentMatchers.<ObjectId>any())).thenReturn(testOrder);
		mvc.perform(get("/orders").queryParam("_id", testOrder.get_id().toHexString())).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]._id.hexString", is(testOrder.get_id().toHexString())));

	}

	@Test(groups = { "orders", "read", "slow" })
	public void getOrdersByCustomerId() throws Exception {
		when(orderServ.getOrdersByCustomerId(ArgumentMatchers.<ObjectId>any())).thenReturn(testOrders);
		mvc.perform(get("/orders").queryParam("cust_id", ObjectId.get().toHexString())).andExpect(status().isOk());
		verify(orderServ, times(1)).getOrdersByCustomerId(ArgumentMatchers.<ObjectId>any());
	}

	@AfterMethod
	public void resetMocks() {
		reset(orderServ);
	}
//
//	@Test(groups = { "orders", "read" })
//	public void getAllOrdersByExample() {
//		when(orderServ.getAllByExample(ArgumentMatchers.<Order>any())).thenReturn(testOrders);
//		assertThat(orderCont.getAllOrdersByExample(testOrder).getBody(), hasItem(testOrder));
//		verify(orderServ, times(1)).getAllByExample(ArgumentMatchers.<Order>any());
//	}
//
//	@Test(groups = { "orders", "update" })
//	public void updateOrderValid() {
//		when(orderServ.save(testOrder)).thenReturn(testOrder);
//		assertEquals(orderCont.updateOrder(new ObjectId(), testOrder),
//				new ResponseEntity<Order>(testOrder, HttpStatus.OK));
//		verify(orderServ, times(1)).save(testOrder);
//		verify(testOrder, times(1)).set_id(ArgumentMatchers.<ObjectId>any());
//	}
//
//	@Test(groups = { "orders", "update", "invalidinput" })
//	public void updateOrderInvalid() {
//		when(orderServ.save(ArgumentMatchers.<Order>any())).thenThrow(IllegalArgumentException.class);
//		assertEquals(orderCont.updateOrder(new ObjectId(), testOrder).getStatusCode(), HttpStatus.BAD_REQUEST);
//		verify(orderServ, times(1)).save(ArgumentMatchers.any());
//		verify(testOrder, times(1)).set_id(ArgumentMatchers.<ObjectId>any());
//	}
//
//	@Test(groups = { "orders", "delete" })
//	public void deleteOrder() {
//		when(orderServ.getOrderBy_id(ArgumentMatchers.<ObjectId>any())).thenReturn(testOrder);
//		doNothing().when(orderServ).delete(ArgumentMatchers.any());
//		orderCont.deleteOrder(new ObjectId());
//		verify(orderServ, times(1)).getOrderBy_id(ArgumentMatchers.<ObjectId>any());
//		verify(orderServ, times(1)).delete(ArgumentMatchers.<Order>any());
//	}
//
//	@AfterMethod
//	public void resetMocks() {
//		reset(orderServ);
//	}
//
//	@AfterMethod
//	public void resetObjects() {
//		testOrder = new Order(new Customer(), null, new ArrayList<Pizza>(Arrays.asList(new Pizza())), 1.5D, 2.5D,
//				OrderType.DELIVERY, new Address());
//	}
}
