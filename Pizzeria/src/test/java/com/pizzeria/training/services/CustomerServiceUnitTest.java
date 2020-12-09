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
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.data.domain.Example;
import org.springframework.test.context.TestExecutionListeners;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pizzeria.training.models.Address;
import com.pizzeria.training.models.Customer;
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.repository.CustomerRepository;
import com.pizzeria.training.service.CustomerService;

@TestExecutionListeners(MockitoTestExecutionListener.class)
public class CustomerServiceUnitTest {

	private CustomerRepository custRepo = mock(CustomerRepository.class);

	private CustomerService custServ = new CustomerService(custRepo);

	private Customer testCust = spy(
			new Customer("email", "password", "firstname", "lastname", "phonenum", mock(Address.class),
					mock(Customer.PaymentCard.class), new ArrayList<Pizza>(Arrays.asList(mock(Pizza.class)))));
	private List<Customer> testCusts = new ArrayList<>(Arrays.asList(testCust));

	@Test(groups = { "customers", "create", "fast", "validinput" })
	public void saveValid() {
		when(custRepo.save(testCust)).thenReturn(testCust);
		assertEquals(custServ.save(testCust), testCust);
		verify(custRepo, times(1)).save(testCust);
	}
	
	@Test(groups = {"customers", "create", "fast", "invalidinput"}, expectedExceptions = IllegalArgumentException.class)
	public void saveThrowIfNull() throws IllegalArgumentException {
		custServ.save(null);
	}
	
	@Test(groups = {"customers", "create", "fast", "invalidinput"}, expectedExceptions = IllegalArgumentException.class)
	public void saveThrowIfMissingEmail() throws IllegalArgumentException {
		when(testCust.getEmail()).thenReturn(null);
		custServ.save(testCust);
	}
	
	@Test(groups = {"customers", "create", "fast", "invalidinput"}, expectedExceptions = IllegalArgumentException.class)
	public void saveThrowIfMissingPassword() throws IllegalArgumentException {
		when(testCust.getPassword()).thenReturn(null);
		custServ.save(testCust);
	}
	
	@Test(groups = {"customers", "create", "fast", "invalidinput"}, expectedExceptions = IllegalArgumentException.class)
	public void saveThrowIfMissingFirstName() throws IllegalArgumentException {
		when(testCust.getFirstName()).thenReturn(null);
		custServ.save(testCust);
	}
	
	@Test(groups = {"customers", "create", "fast", "invalidinput"}, expectedExceptions = IllegalArgumentException.class)
	public void saveThrowIfMissingLastName() throws IllegalArgumentException {
		when(testCust.getLastName()).thenReturn(null);
		custServ.save(testCust);
	}
	
	@Test(groups = {"customers", "create", "fast", "invalidinput"}, expectedExceptions = IllegalArgumentException.class)
	public void saveThrowIfMissingPhoneNumber() throws IllegalArgumentException {
		when(testCust.getPhoneNum()).thenReturn(null);
		custServ.save(testCust);
	}
	
	@Test(groups = { "customers", "read" })
	public void findAll() {
		when(custRepo.findAll()).thenReturn(testCusts);
		assertThat(custServ.findAll(), hasItem(testCust));
		verify(custRepo, times(1)).findAll();
	}

	@Test(groups = { "customers", "read" })
	public void findById() {
		ObjectId testId = new ObjectId();
		when(custRepo.findBy_id(testId)).thenReturn(testCust);
		assertEquals(custServ.getCustomerBy_id(testId), testCust);
		verify(custRepo, times(1)).findBy_id(testId);
	}

	@Test(groups = { "customers", "read" })
	public void findByExample() {
		when(custRepo.findAll(ArgumentMatchers.<Example<Customer>>any())).thenReturn(testCusts);
		assertThat(custServ.findAllByExample(testCust), hasItem(testCust));
		verify(custRepo, times(1)).findAll(ArgumentMatchers.<Example<Customer>>any());
	}

	@Test(groups = { "customers", "read" })
	public void findByCity() {
		when(custRepo.findByHomeAddressCity(ArgumentMatchers.anyString())).thenReturn(testCusts);
		assertThat(custServ.getAllByCity(""), hasItem(testCust));
		verify(custRepo, times(1)).findByHomeAddressCity(ArgumentMatchers.anyString());
	}

	@Test(groups = { "customers", "update" })
	public void updateOperations() {
		ObjectId testId = new ObjectId();
		Pizza testPizza = mock(Pizza.class);
		List<Pizza> testPizzas = new ArrayList<>(Arrays.asList(testPizza));

		when(custRepo.findBy_id(testId)).thenReturn(testCust);
		when(custRepo.save(testCust)).thenReturn(testCust);
		when(testCust.getFavoriteOrder()).thenReturn(testPizzas);

		try {
			assertThat(custServ.updateFavoriteOrder(testId, testPizzas), hasItem(testPizza));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		verify(custRepo, times(1)).findBy_id(testId);
		verify(custRepo, times(1)).save(testCust);
		verify(testCust, times(1)).setFavoriteOrder(testPizzas);
		verify(testCust, times(2)).getFavoriteOrder();
	}

	@Test(groups = { "customers", "delete" })
	public void deleteOperations() {
		doNothing().when(custRepo).delete(testCust);
		custServ.delete(testCust);
		verify(custRepo, times(1)).delete(testCust);
	}

	@AfterMethod
	public void resetMocks() {
		reset(custRepo);
		reset(testCust);
	}
}
