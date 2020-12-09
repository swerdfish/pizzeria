package com.pizzeria.training.models;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

public class AddressModelTest {

	private Address testAddr;
	private String testStreet1 = "street 1";
	private String testStreet2 = "street 2";
	private String testCity = "city";
	private String testState = "state";
	private String testPostal = "postal";
	
	@Test(groups = {"othermodels", "fast"})
	public void noArgsConstructor() {
		testAddr = new Address();
		
		assertNull(testAddr.getStreetAddress());
		assertNull(testAddr.getStreetAddressLine2());
		assertNull(testAddr.getCity());
		assertNull(testAddr.getState());
		assertNull(testAddr.getPostal());
	}
	
	@Test(groups = {"othermodels", "fast"})
	public void setters() {
		testAddr = new Address();
		
		testAddr.setStreetAddress(testStreet1);
		testAddr.setStreetAddressLine2(testStreet2);
		testAddr.setCity(testCity);
		testAddr.setState(testState);
		testAddr.setPostal(testPostal);
		
		assertEquals(testAddr.getStreetAddress(), testStreet1);
		assertEquals(testAddr.getStreetAddressLine2(), testStreet2);
		assertEquals(testAddr.getCity(), testCity);
		assertEquals(testAddr.getState(), testState);
		assertEquals(testAddr.getPostal(), testPostal);
	}
	
	@Test(groups = {"othermodels", "fast"})
	public void allArgsConstructor() {
		testAddr = new Address(testStreet1, testStreet2, testCity, testState, testPostal);
		
		assertEquals(testAddr.getStreetAddress(), testStreet1);
		assertEquals(testAddr.getStreetAddressLine2(), testStreet2);
		assertEquals(testAddr.getCity(), testCity);
		assertEquals(testAddr.getState(), testState);
		assertEquals(testAddr.getPostal(), testPostal);
	}
	
	@Test(groups = {"othermodels", "fast"})
	public void equalsHashCode() {
		Address a1 = new Address(testStreet1, testStreet2, testCity, testState, testPostal);
		Address a2 = new Address(testStreet1, testStreet2, testCity, testState, testPostal);
		assertTrue(a1.equals(a2));
		assertTrue(a1.hashCode() == a2.hashCode());
		
		a2 = new Address();
		assertFalse(a1.equals(a2));
		assertFalse(a1.hashCode() == a2.hashCode());
	}
	
	@Test(groups = {"othermodels", "fast"})
	public void toStringTest() {
		assertTrue(new Address(testStreet1, null, null, null, null).toString().contains(testStreet1));
	}
	
}
	
