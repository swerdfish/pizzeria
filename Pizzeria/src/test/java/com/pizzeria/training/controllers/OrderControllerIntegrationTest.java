package com.pizzeria.training.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzeria.training.exception.ControllerAdvisor;
import com.pizzeria.training.models.Address;
import com.pizzeria.training.models.Customer;
import com.pizzeria.training.models.Order;
import com.pizzeria.training.models.OrderStatus;
import com.pizzeria.training.models.OrderType;
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.service.CustomerService;
import com.pizzeria.training.service.OrderService;
import com.pizzeria.training.util.serializers.ObjectIdSerializer;

@WebMvcTest
@ContextConfiguration(classes = { OrderController.class, ObjectIdSerializer.class, ControllerAdvisor.class })
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
public class OrderControllerIntegrationTest extends AbstractTestNGSpringContextTests {

	@MockBean
	private OrderService orderServ;
	@MockBean
	private CustomerService custServ;

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper objMap;

	private Order testOrder;
	private List<Order> testOrders;
	private Customer testCust;
	private List<Customer> testCusts;

	@BeforeMethod
	public void initializeOrderObject() {
		testOrder = new Order(new Customer(), null, new ArrayList<Pizza>(Arrays.asList(new Pizza())), 1.5D, 2.5D,
				OrderStatus.PENDING, OrderType.DELIVERY, new Address());
		testOrders = new ArrayList<>(Arrays.asList(testOrder));
		testCust = new Customer("email", "password", "first", "last", "phone", new Address(),
				new Customer.PaymentCard(), new ArrayList<Pizza>(Arrays.asList(new Pizza())));
		testCusts = new ArrayList<>(Arrays.asList(testCust));
	}

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

	@Test(groups = { "orders", "read", "slow" })
	public void getOrdersByCustomerEmail() throws Exception {
		Customer emailOnly = new Customer(testCust.getEmail(), null, null, null, null, null, null, null);
		when(custServ.findAllByExample(emailOnly)).thenReturn(testCusts);
		when(orderServ.getOrdersByCustomerId(ArgumentMatchers.<ObjectId>any())).thenReturn(testOrders);
		mvc.perform(get("/orders").queryParam("email", testCust.getEmail())).andExpect(status().isOk());
		verify(custServ, times(1)).findAllByExample(emailOnly);
		verify(orderServ, times(1)).getOrdersByCustomerId(ArgumentMatchers.<ObjectId>any());
	}

	@Test(groups = { "orders", "read", "slow" })
	public void getAllOrdersByExample() throws Exception {
		testOrder.set_id(new ObjectId());
		when(orderServ.getAllByExample(ArgumentMatchers.<Order>any())).thenReturn(testOrders);
		mvc.perform(post("/orders/examples").contentType(MediaType.APPLICATION_JSON)
				.content(objMap.writeValueAsString(testOrder))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]._id.hexString", is(testOrder.get_id().toHexString())));
		verify(orderServ, times(1)).getAllByExample(ArgumentMatchers.<Order>any());
	}

	@Test(groups = { "orders", "update", "validinput", "slow" })
	public void updateOrderValid() throws Exception {
		testOrder.set_id(new ObjectId());
		when(orderServ.save(ArgumentMatchers.<Order>any())).thenReturn(testOrder);
		mvc.perform(put("/orders").queryParam("_id", testOrder.get_id().toHexString())
				.contentType(MediaType.APPLICATION_JSON).content(objMap.writeValueAsString(testOrder)))
				.andExpect(status().isOk()).andExpect(jsonPath("$").exists());
		verify(orderServ, times(1)).save(testOrder);
	}

	@Test(groups = { "orders", "update", "invalidinput", "slow" })
	public void updateOrderInvalid() throws Exception {
		testOrder.set_id(new ObjectId());
		when(orderServ.save(ArgumentMatchers.<Order>any())).thenThrow(IllegalArgumentException.class);
		mvc.perform(put("/orders").queryParam("_id", testOrder.get_id().toHexString())
				.contentType(MediaType.APPLICATION_JSON).content(objMap.writeValueAsString(testOrder)))
				.andExpect(status().isBadRequest());
		verify(orderServ, times(1)).save(ArgumentMatchers.<Order>any());
	}

	@Test(groups = { "orders", "delete", "slow" })
	public void deleteOrder() throws Exception {
		testOrder.set_id(new ObjectId());
		when(orderServ.getOrderBy_id(ArgumentMatchers.<ObjectId>any())).thenReturn(testOrder);
		doNothing().when(orderServ).delete(ArgumentMatchers.any());
		mvc.perform(delete("/orders").queryParam("_id", testOrder.get_id().toHexString())).andExpect(status().isOk());
		verify(orderServ, times(1)).getOrderBy_id(ArgumentMatchers.<ObjectId>any());
		verify(orderServ, times(1)).delete(ArgumentMatchers.<Order>any());
	}

	@AfterMethod
	public void resetMocks() {
		reset(orderServ);
		reset(custServ);
	}
}
