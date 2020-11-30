package com.pizzeria.training.models;

import static org.junit.Assert.assertNull;
import static org.testng.Assert.assertEquals;

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
  
  @Test
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
	  testCard = new Customer.PaymentCard(testCardNumber, testExpiration, testSecurity, testAddress);
	  
	  assertEquals(testCard.getClass(), Customer.PaymentCard.class);
	  assertEquals(testCard.getCardNumber(), testCardNumber);
	  assertEquals(testCard.getExpiration(), testExpiration); 
	  assertEquals(testCard.getSecurityCode(), testSecurity);
	  assertEquals(testCard.getBillingAddress(), testAddress);
  }
  
  @Test
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
  
  @Test
  public void allArgsConstructor() {
	  List<Pizza> testFavorite = Arrays.asList(new Pizza(), new Pizza());
	  ObjectId test_id = new ObjectId();
	  testCust = new Customer(test_id, testMail, testPassword, testFirst, testLast, testPhone, testAddress, testCard, testFavorite);
	  
	  assertEquals(testCust.getClass(), Customer.class);
	  assertEquals(testCust.get_id(), test_id);
	  assertEquals(testCust.getPhoneNum(), testPhone);
	  assertEquals(testCust.getEmail(), testMail);
	  assertEquals(testCust.getHomeAddress(), testAddress);
	  assertEquals(testCust.getCard(), testCard);
  }
}
