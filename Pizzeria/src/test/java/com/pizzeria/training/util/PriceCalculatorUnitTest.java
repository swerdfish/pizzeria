package com.pizzeria.training.util;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.testng.annotations.Test;

import com.pizzeria.training.models.Order;
import com.pizzeria.training.models.OrderType;
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.models.PizzaSize;
import com.pizzeria.training.models.PizzaType;
import com.pizzeria.training.models.Toppings;

public class PriceCalculatorUnitTest {

	private Double[][] cheesePriceMatrix;
	Pizza testPizza1 = spy(new Pizza(PizzaType.THIN_CRUST,
			new HashSet<Toppings>(Arrays.asList(Toppings.CHEESE, Toppings.PEPPERONI)), null, PizzaSize.LARGE));
	Pizza testPizza2 = spy(new Pizza(PizzaType.DEEP_DISH,
			new HashSet<Toppings>(Arrays.asList(Toppings.ANCHOVY, Toppings.MUSHROOM)), null, PizzaSize.SMALL));
	Order testOrder = spy(new Order(null, null, new ArrayList<Pizza>(Arrays.asList(testPizza1, testPizza2)), null, 1.0d,
			null, OrderType.DELIVERY, null));

	@Test(groups = { "fast", "util" })
	public void getCheesePriceMatrix() {
		cheesePriceMatrix = PriceCalculator.getCheesePrices();
	}

	@Test(groups = { "fast", "util" }, dependsOnMethods = "getCheesePriceMatrix")
	public void getSingleCheesePrice() {
		for (int i = 0; i < PizzaType.values().length; i++) {
			for (int j = 0; j < PizzaSize.values().length; j++) {
				assertEquals(PriceCalculator.getCheesePrice(PizzaType.values()[i], PizzaSize.values()[j]),
						cheesePriceMatrix[i][j]);
			}
		}
	}

	@Test(groups = { "fast", "util" }, dependsOnMethods = "getSingleCheesePrice")
	public void getSinglePriceWithToppings() {
		double expectedPrice = PriceCalculator.getCheesePrice(PizzaType.THIN_CRUST, PizzaSize.LARGE)
				+ (((Toppings.CHEESE.getCostPerSize() + Toppings.PEPPERONI.getCostPerSize())
						* (PizzaSize.LARGE.getIndex() + 1)) * 0.50D);
		assertEquals(PriceCalculator.staticCalculatePrice(testPizza1), expectedPrice);
		verify(testPizza1, atLeast(1)).getSize();
		verify(testPizza1, atLeast(1)).getToppings();
		verify(testPizza1, atLeast(1)).getType();
	}

	@Test(groups = { "fast", "util" }, dependsOnMethods = "getSinglePriceWithToppings")
	public void getMultiplePricesWithToppings() {
		double expectedPrice = PriceCalculator.staticCalculatePrice(testPizza1)
				+ PriceCalculator.staticCalculatePrice(testPizza2);
		assertEquals(PriceCalculator.staticCalculatePrice(Arrays.asList(testPizza1, testPizza2)), expectedPrice);
		verify(testPizza1, atLeast(1)).getCost();
		verify(testPizza2, atLeast(1)).getCost();
	}
	
	@Test(groups = {"fast", "util"}, dependsOnMethods = "getMultiplePricesWithToppings")
	public void getSingleOrderPrice() {
		double expectedPrice = PriceCalculator.staticCalculatePrice(testOrder.getPizzas()) + testOrder.getType().getExtraCost();
		assertEquals(PriceCalculator.staticCalculatePrice(testOrder), expectedPrice);
		verify(testOrder, atLeast(1)).getPizzas();
		verify(testOrder, atLeast(1)).getType();
	}

}
