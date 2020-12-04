package com.pizzeria.training.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.service.CustomerService;
import com.pizzeria.training.util.serializers.ObjectIdSerializer;

@WebMvcTest
@ContextConfiguration(classes = { CustomerController.class, ObjectIdSerializer.class, ControllerAdvisor.class })
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
public class CustomerControllerIntegrationTest extends AbstractTestNGSpringContextTests {

	@MockBean
	private CustomerService customerServ;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objMap;

	private Customer testCustomer;
	private List<Customer> testCustomers;

	@BeforeMethod
	public void initializeCustomerObject() {
		testCustomer = new Customer("email", "password", "firstname", "lastname", "phonenum", new Address(),
				new Customer.PaymentCard(), new ArrayList<Pizza>(Arrays.asList(new Pizza())));
		testCustomers = new ArrayList<>(Arrays.asList(testCustomer));
	}

	@Test(enabled = false)
	public void contextLoads() {
	}

	@Test(enabled = false)
	public void testEndpoint() throws Exception {
		mvc.perform(get("/customers/test")).andExpect(status().isOk());
	}

	@Test(groups = { "customers", "create", "validinput", "slow" })
	public void createNewValid() throws Exception {
		testCustomer.set_id(new ObjectId());
		when(customerServ.save(ArgumentMatchers.<Customer>any())).thenReturn(testCustomer);
		mvc.perform(
				post("/customers").contentType(MediaType.APPLICATION_JSON).content(objMap.writeValueAsString(testCustomer)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("_id.timestamp", is(testCustomer.get_id().getTimestamp())));
		verify(customerServ, times(1)).save(ArgumentMatchers.<Customer>any());
	}

	@Test(groups = { "customers", "create", "invalidinput", "slow" })
	public void createNewInvalidCustomer() throws Exception {
		when(customerServ.save(ArgumentMatchers.<Customer>any())).thenThrow(IllegalArgumentException.class);
		mvc.perform(
				post("/customers").contentType(MediaType.APPLICATION_JSON).content(objMap.writeValueAsString(testCustomer)))
				.andExpect(status().isBadRequest())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
	}

	@Test(groups = { "customers", "read", "slow" })
	public void getAllCustomers() throws Exception {
		testCustomer.set_id(new ObjectId());
		when(customerServ.findAll()).thenReturn(testCustomers);
		mvc.perform(get("/customers")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]._id.hexString", is(testCustomer.get_id().toHexString())));

	}

	@Test(groups = { "customers", "read", "slow" })
	public void getCustomerById() throws Exception {
		testCustomer.set_id(new ObjectId());
		when(customerServ.getCustomerBy_id(ArgumentMatchers.<ObjectId>any())).thenReturn(testCustomer);
		mvc.perform(get("/customers").queryParam("_id", testCustomer.get_id().toHexString())).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]._id.hexString", is(testCustomer.get_id().toHexString())));

	}

	@Test(groups = { "customers", "read", "slow" })
	public void getAllCustomersByExample() throws Exception {
		testCustomer.set_id(new ObjectId());
		when(customerServ.findAllByExample(ArgumentMatchers.<Customer>any())).thenReturn(testCustomers);
		mvc.perform(post("/customers/examples").contentType(MediaType.APPLICATION_JSON)
				.content(objMap.writeValueAsString(testCustomer))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]._id.hexString", is(testCustomer.get_id().toHexString())));
		verify(customerServ, times(1)).findAllByExample(ArgumentMatchers.<Customer>any());
	}

	@Test(groups = { "customers", "update", "validinput", "slow" })
	public void updateCustomerValid() throws Exception {
		testCustomer.set_id(new ObjectId());
		when(customerServ.save(ArgumentMatchers.<Customer>any())).thenReturn(testCustomer);
		mvc.perform(put("/customers").queryParam("_id", testCustomer.get_id().toHexString())
				.contentType(MediaType.APPLICATION_JSON).content(objMap.writeValueAsString(testCustomer)))
				.andExpect(status().isOk()).andExpect(jsonPath("$").exists());
		verify(customerServ, times(1)).save(testCustomer);
	}

	@Test(groups = { "customers", "update", "invalidinput", "slow" })
	public void updateCustomerInvalid() throws Exception {
		testCustomer.set_id(new ObjectId());
		when(customerServ.save(ArgumentMatchers.<Customer>any())).thenThrow(IllegalArgumentException.class);
		mvc.perform(put("/customers").queryParam("_id", testCustomer.get_id().toHexString())
				.contentType(MediaType.APPLICATION_JSON).content(objMap.writeValueAsString(testCustomer)))
				.andExpect(status().isBadRequest());
		verify(customerServ, times(1)).save(ArgumentMatchers.<Customer>any());
	}

	@Test(groups = { "customers", "delete", "slow"})
	public void deleteCustomer() throws Exception {
		testCustomer.set_id(new ObjectId());
		when(customerServ.getCustomerBy_id(ArgumentMatchers.<ObjectId>any())).thenReturn(testCustomer);
		doNothing().when(customerServ).delete(ArgumentMatchers.any());
		mvc.perform(delete("/customers").queryParam("_id", testCustomer.get_id().toHexString())).andExpect(status().isOk());
		verify(customerServ, times(1)).getCustomerBy_id(ArgumentMatchers.<ObjectId>any());
		verify(customerServ, times(1)).delete(ArgumentMatchers.<Customer>any());
	}

	@AfterMethod
	public void resetMocks() {
		reset(customerServ);
	}
}
