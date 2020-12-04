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

import com.pizzeria.training.models.Customer;
import com.pizzeria.training.models.Order;
import com.pizzeria.training.service.CustomerService;

public class CustomerControllerUnitTest {

	private CustomerService custServ = mock(CustomerService.class);
	private CustomerController custCont = new CustomerController(custServ);

	private Customer testCust = mock(Customer.class);
	private List<Customer> testCusts = new ArrayList<>(Arrays.asList(testCust));

	@Test(groups = { "customers", "utility", "fast" })
	public void testEndpoint() {
		assertEquals(custCont.test(), new ResponseEntity<String>("Customer Endpoint works", HttpStatus.OK));
	}

	@Test(groups = { "customers", "create", "validinput", "fast" })
	public void createNewValid() {
		when(custServ.save(testCust)).thenReturn(testCust);
		assertEquals(custCont.newCustomer(testCust), new ResponseEntity<Customer>(testCust, HttpStatus.CREATED));
		verify(custServ, times(1)).save(testCust);
	}

	@Test(groups = { "customers", "create", "invalidinput", "fast" }, expectedExceptions = IllegalArgumentException.class)
	public void createNewInvalidCustomer() {
		when(custServ.save(testCust)).thenThrow(IllegalArgumentException.class);
		custCont.newCustomer(testCust);
	}

	@Test(groups = { "customers", "read", "fast" })
	public void getAllCustomers() {
		when(custServ.findAll()).thenReturn(testCusts);
		assertThat(custCont.getCustomers(null, null).getBody(), hasItem(testCust));
		verify(custServ, times(1)).findAll();
	}

	@Test(groups = { "customers", "read", "fast" })
	public void getCustomerById() {
		when(custServ.getCustomerBy_id(ArgumentMatchers.<ObjectId>any())).thenReturn(testCust);
		assertThat(custCont.getCustomers(new ObjectId(), null).getBody(), hasItem(testCust));
		verify(custServ, times(1)).getCustomerBy_id(ArgumentMatchers.<ObjectId>any());
	}

	@Test(groups = { "customers", "read", "fast" })
	public void getAllCustomersByExample() {
		when(custServ.findAllByExample(ArgumentMatchers.<Customer>any())).thenReturn(testCusts);
		assertThat(custCont.getAllCustomersByExample(testCust).getBody(), hasItem(testCust));
		verify(custServ, times(1)).findAllByExample(ArgumentMatchers.<Customer>any());
	}

	@Test(groups = { "customers", "update", "fast" })
	public void updateCustomerValid() {
		when(custServ.save(testCust)).thenReturn(testCust);
		assertEquals(custCont.updateCustomer(new ObjectId(), testCust),
				new ResponseEntity<Customer>(testCust, HttpStatus.OK));
		verify(custServ, times(1)).save(testCust);
		verify(testCust, times(1)).set_id(ArgumentMatchers.<ObjectId>any());
	}

	@Test(groups = { "customers", "update", "invalidinput", "fast" }, expectedExceptions = IllegalArgumentException.class)
	public void updateCustomerInvalid() {
		when(custServ.save(ArgumentMatchers.<Customer>any())).thenThrow(IllegalArgumentException.class);
		custCont.updateCustomer(new ObjectId(), testCust);
		verify(custServ, times(1)).save(ArgumentMatchers.any());
		verify(testCust, times(1)).set_id(ArgumentMatchers.<ObjectId>any());
	}

	@Test(groups = { "customers", "delete", "fast" })
	public void deleteCustomer() {
		when(custServ.getCustomerBy_id(ArgumentMatchers.<ObjectId>any())).thenReturn(testCust);
		doNothing().when(custServ).delete(ArgumentMatchers.any());
		custCont.deleteCustomer(new ObjectId());
		verify(custServ, times(1)).getCustomerBy_id(ArgumentMatchers.<ObjectId>any());
		verify(custServ, times(1)).delete(ArgumentMatchers.<Customer>any());
	}

	@AfterMethod
	public void resetMocks() {
		reset(custServ);
		reset(testCust);
	}
}
