package com.pizzeria.training.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.mockito.Mockito;
import org.testng.annotations.Test;

public class CustomerModelTest {
  private Customer testCust;
  
  String testMail = "test email";
  String testPassword = "test password";
  String testFirst = "test first";
  String testLast = "test last";
  String testPhone = "test phone";
  
  Address testAddress = Mockito.mock(Address.class);
  
  Long testCardNumber = 1L;
  String testExpiration = "expiration";
  Short testSecurity = 1;
  Customer.PaymentCard testCard;
  
  @Test(groups = {"customers", "fast"})
  public void noArgsConstructor() {
	  testCust = new Customer();
	  
	  assertEquals(testCust.getClass(), Customer.class);
	  assertNull(testCust.get_id());
	  assertNull(testCust.getHomeAddress());
	  assertNull(testCust.getCard());
	  assertNull(testCust.getEmail());
	  assertNull(testCust.getFavoriteOrder());
	  assertNull(testCust.getPhoneNum());
  }
  
  @Test(groups = {"customers", "fast"})
  public void cardNoArgs() {
	  Customer.PaymentCard testCard = new Customer.PaymentCard();
	  
	  assertEquals(testCard.getClass(), Customer.PaymentCard.class);
	  assertNull(testCard.getCardNumber());
	  assertNull(testCard.getExpiration());
	  assertNull(testCard.getSecurityCode());
  }
  
  @Test(groups = {"customers", "fast"})
  public void cardAllArgs() {
	  testCard = new Customer.PaymentCard(testCardNumber, testExpiration, testSecurity, testAddress);
	  
	  assertEquals(testCard.getClass(), Customer.PaymentCard.class);
	  assertEquals(testCard.getCardNumber(), testCardNumber);
	  assertEquals(testCard.getExpiration(), testExpiration); 
	  assertEquals(testCard.getSecurityCode(), testSecurity);
	  assertEquals(testCard.getBillingAddress(), testAddress);
  }
  
  @Test(groups = {"customers", "fast"}, dependsOnMethods = "cardNoArgs")
  public void cardSetter() {
	  testCard = new Customer.PaymentCard();
	  
	  testCard.setCardNumber(testCardNumber);
	  testCard.setExpiration(testExpiration);
	  testCard.setSecurityCode(testSecurity);
	  testCard.setBillingAddress(testAddress);
	  
	  assertEquals(testCard.getCardNumber(), testCardNumber);
	  assertEquals(testCard.getExpiration(), testExpiration);
	  assertEquals(testCard.getBillingAddress(), testAddress);
  }
  
  @Test(groups = {"customers", "fast"}, dependsOnMethods = {"cardNoArgs", "cardAllArgs"})
  public void cardEqualsHashCode() {
	  Customer.PaymentCard c1 = new Customer.PaymentCard(testCardNumber, testExpiration, testSecurity, testAddress);
	  Customer.PaymentCard c2 = new Customer.PaymentCard(testCardNumber, testExpiration, testSecurity, testAddress);
	  assertTrue(c1.equals(c2));
	  assertTrue(c1.hashCode() == c2.hashCode());
	  
	  c2 = new Customer.PaymentCard();
	  assertFalse(c1.equals(c2));
	  assertFalse(c1.hashCode() == c2.hashCode());
  }
  
  
  
  @Test(groups = {"customers", "fast"}, dependsOnMethods = "noArgsConstructor")
  public void setters() {
	  testCust = new Customer();
	  ObjectId test_id = new ObjectId();
	  List<Pizza> testFavorite = Arrays.asList(new Pizza(), new Pizza());
	  testCust.set_id(test_id);
	  testCust.setEmail(testMail);
	  testCust.setPassword(testPassword);
	  testCust.setFirstName(testFirst);
	  testCust.setLastName(testLast);
	  testCust.setPhoneNum(testPhone);
	  testCust.setHomeAddress(testAddress);
	  testCust.setCard(testCard);
	  testCust.setFavoriteOrder(testFavorite);
	  
	  assertEquals(testCust.getClass(), Customer.class);
	  assertEquals(testCust.get_id(), test_id);
	  assertEquals(testCust.getPassword(), testPassword);
	  assertEquals(testCust.getFirstName(), testFirst);
	  assertEquals(testCust.getLastName(), testLast);
	  assertEquals(testCust.getPhoneNum(), testPhone);
	  assertEquals(testCust.getEmail(), testMail);
	  assertEquals(testCust.getHomeAddress(), testAddress);
	  assertEquals(testCust.getCard(), testCard);
	  assertThat(testCust.getFavoriteOrder(), is(testFavorite));
  }
  
  @Test(groups = {"customers", "fast"})
  public void allArgsConstructor() {
	  List<Pizza> testFavorite = Arrays.asList(new Pizza(), new Pizza());
	  ObjectId test_id = new ObjectId();
	  testCust = new Customer(test_id, testMail, testPassword, testFirst, testLast, testPhone, testAddress, testCard, testFavorite);
	  
	  assertEquals(testCust.getClass(), Customer.class);
	  assertEquals(testCust.get_id(), test_id);
	  assertEquals(testCust.getPassword(), testPassword);
	  assertEquals(testCust.getFirstName(), testFirst);
	  assertEquals(testCust.getLastName(), testLast);
	  assertEquals(testCust.getPhoneNum(), testPhone);
	  assertEquals(testCust.getEmail(), testMail);
	  assertEquals(testCust.getHomeAddress(), testAddress);
	  assertEquals(testCust.getCard(), testCard);
	  assertThat(testCust.getFavoriteOrder(), is(testFavorite));
  }
  
  @Test(groups = {"customers", "fast"}, dependsOnMethods = {"noArgsConstructor", "allArgsConstructor"})
  public void equalsHashCode() {
	  List<Pizza> testFavorite = Arrays.asList(new Pizza(), new Pizza());
	  ObjectId test_id = new ObjectId();
	  Customer cust1 = new Customer(test_id, testMail, testPassword, testFirst, testLast, testPhone, testAddress, testCard, testFavorite);
	  Customer cust2 = new Customer(test_id, testMail, testPassword, testFirst, testLast, testPhone, testAddress, testCard, testFavorite);
	  assertTrue(cust1.equals(cust2));
	  assertTrue(cust1.hashCode() == cust2.hashCode());
	  
	  cust2 = new Customer();
	  assertFalse(cust1.equals(cust2));
	  assertFalse(cust1.hashCode() == cust2.hashCode());
  }
  
  @Test(groups = {"customers", "fast"})
  public void toStringTest() {
	  assertTrue(new Customer().toString().contains("_id"));
  }
}
