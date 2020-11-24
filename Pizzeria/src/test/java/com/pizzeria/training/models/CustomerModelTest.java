package com.pizzeria.training.models;

import static org.junit.Assert.assertNull;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class CustomerModelTest {
  private Customer testCust;
  
  Long testPhone = 1L;
  String testMail = "test email";
  
  String testStreet = "street";
  String testStreet2 = "street2";
  String testCity = "city";
  String testState = "state";
  String testPostal = "postal";
  
  Long testNumber = 1L;
  String testExpiration = "expiration";
  Short testSecurity = 1;
  
  @Test
  public void noArgsConstructor() {
	  testCust = new Customer();
	  
	  assertEquals(testCust.getClass(), Customer.class);
	  assertNull(testCust.get_id());
	  assertNull(testCust.getAddress());
	  assertNull(testCust.getCard());
	  assertNull(testCust.getEmail());
	  assertNull(testCust.getFavoriteOrder());
	  assertNull(testCust.getPhoneNum());
  }
  
  @Test
  public void addressNoArgs() {
	  Customer.HomeAddress testAddress = new Customer.HomeAddress();
	  
	  assertEquals(testAddress.getClass(), Customer.HomeAddress.class);
	  assertNull(testAddress.getStreetAddress());
	  assertNull(testAddress.getCity());
	  assertNull(testAddress.getState());
	  assertNull(testAddress.getPostal());
  }
  
  @Test
  public void addressAllArgs() {
	  Customer.HomeAddress testAddress = new Customer.HomeAddress(testStreet, testStreet2, testCity, testState, testPostal);
	  
	  assertEquals(testAddress.getClass(), Customer.HomeAddress.class);
	  assertEquals(testAddress.getStreetAddress(), testStreet);
	  assertEquals(testAddress.getStreetAddressLine2(), testStreet2);
	  assertEquals(testAddress.getCity(), testCity);
	  assertEquals(testAddress.getState(), testState);
	  assertEquals(testAddress.getPostal(), testPostal);
  }
  
  @Test
  public void addessSetters() {
	  Customer.HomeAddress testAddress = new Customer.HomeAddress();
	  
	  testAddress.setStreetAddress(testStreet);
	  testAddress.setStreetAddressLine2(testStreet2);
	  testAddress.setCity(testCity);
	  testAddress.setState(testState);
	  testAddress.setPostal(testPostal);
	  
	  assertEquals(testAddress.getStreetAddress(), testStreet);
	  assertEquals(testAddress.getStreetAddressLine2(), testStreet2);
	  assertEquals(testAddress.getCity(), testCity);
	  assertEquals(testAddress.getState(), testState);
	  assertEquals(testAddress.getPostal(), testPostal);
  }
  
  @Test
  public void cardNoArgs() {
	  Customer.PaymentCard testCard = new Customer.PaymentCard();
	  
	  assertEquals(testCard.getClass(), Customer.PaymentCard.class);
	  assertNull(testCard.getCardNumber());
	  assertNull(testCard.getExpiration());
	  assertNull(testCard.getSecurityCode());
  }
  
  @Test
  public void cardAllArgs() {
	  Customer.PaymentCard testCard = new Customer.PaymentCard(testNumber, testExpiration, testSecurity);
	  
	  assertEquals(testCard.getClass(), Customer.PaymentCard.class);
	  assertEquals(testCard.getCardNumber(), testNumber);
	  assertEquals(testCard.getExpiration(), testExpiration);
	  assertEquals(testCard.getSecurityCode(), testSecurity);
  }
  
  @Test
  public void cardSetter() {
	  Customer.PaymentCard testCard = new Customer.PaymentCard();
	  
	  testCard.setCardNumber(testNumber);
	  testCard.setExpiration(testExpiration);
	  testCard.setSecurityCode(testSecurity);
	  
	  assertEquals(testCard.getCardNumber(), testNumber);
	  assertEquals(testCard.getExpiration(), testExpiration);
	  assertEquals(testCard.getSecurityCode(), testSecurity);
  }
  
  @Test
  public void allArgsConstructor() {
	  Customer.HomeAddress testAddress = new Customer.HomeAddress(testStreet, testStreet2, testCity, testState, testPostal);
	  Customer.PaymentCard testCard = new Customer.PaymentCard(testNumber, testExpiration, testSecurity);
	  
	  testCust = new Customer(testPhone, testMail, testAddress, testCard);
	  
	  assertEquals(testCust.getClass(), Customer.class);
	  assertNull(testCust.get_id());
	  assertEquals(testCust.getPhoneNum(), testPhone);
	  assertEquals(testCust.getEmail(), testMail);
	  assertEquals(testCust.getAddress(), testAddress);
	  assertEquals(testCust.getCard(), testCard);
  }
}
