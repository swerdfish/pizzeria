package com.pizzeria.training.controllers;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.pizzeria.training.models.Customer;
import com.pizzeria.training.service.CustomerService;

public class CustomerControllerUnitTest {

	private CustomerService custServ = mock(CustomerService.class);
	private CustomerController custCont = new CustomerController(custServ);
	
	private Customer testCust = mock(Customer.class);
	private List<Customer> testCusts = new ArrayList<>(Arrays.asList(testCust));
	
	@Test(enabled = false, groups = {"customers", "utility", "fast"})
	public void testEndpoint() {
		assertEquals(custCont.test(), "Customer Endpoint works");
	}
}
