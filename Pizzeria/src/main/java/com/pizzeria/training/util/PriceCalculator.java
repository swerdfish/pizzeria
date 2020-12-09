package com.pizzeria.training.util;

import java.util.Collection;
import java.util.HashSet;

import com.pizzeria.training.models.Order;
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.models.PizzaSize;
import com.pizzeria.training.models.PizzaType;
import com.pizzeria.training.models.Toppings;

/**
 * Class for determining the price of a pizza
 */
public class PriceCalculator {

	private static final Double[][] typeSizeCostMatrix;

	static {
		typeSizeCostMatrix = new Double[][] {
				// slice person small medium large extra-large
				{ 3.99D, null, null, null, 16.95D, null }, // classic
				{ null, null, 8.55D, 12.35D, 15.35D, 18.45D }, // thin crust
				{ null, 8.05D, 12.35D, 16.45D, 20.55D, null }, // deep dish
				{ null, null, 10.99D, 13.99D, 16.99D, 18.99D }, // sicilian
				{ null, null, 12.75D, 14.50D, 17.75D, 20.25D }, // stuffed
				{ null, null, 11.29D, null, null, null } }; // gluten-free
	}

	/**
	 * Returns a 2-D ArrayList where the rows are PizzaTypes and the columns are
	 * Sizes. In order, the rows are classic, thin crust, deep dish, sicilian,
	 * stuffed, gluten-free. In order, the columns are slice, personal (8"), small
	 * (10"), medium (12"), large (14"), xlarge (16"). The value may be null if we
	 * do not offer that combination of PizzaType and Size
	 * 
	 * @return
	 */
	public static Double[][] getCheesePrices() {
		return typeSizeCostMatrix;
	}

	public static Double getCheesePrice(PizzaType type, PizzaSize size) {
		return typeSizeCostMatrix[type.getIndex()][size.getIndex()];
	}

	public static Double staticCalculatePrice(PizzaType type, PizzaSize size, HashSet<Toppings> toppings) {
		double totalPrice = getCheesePrice(type, size);
		for (Toppings t : toppings) {
			totalPrice += (t.getCostPerSize() * (size.getIndex() + 1) * 0.50D);
		}
		return totalPrice;
	}

	public static Double staticCalculatePrice(Pizza p) {
		return staticCalculatePrice(p.getType(), p.getSize(), new HashSet<Toppings>(p.getToppings()));
	}
	
	public static Double staticCalculatePrice(Collection<Pizza> pColl) {
		Double total = 0.0D;
		for (Pizza p : pColl) {
			if (p.getCost() == null) {
				p.setCost(staticCalculatePrice(p));
			}
			total += p.getCost();
		}
		return total;
	}

	public static Double staticCalculatePrice(Order o) {
		return staticCalculatePrice(o.getPizzas()) + o.getType().getExtraCost();
	}

	public Double calculatePrice(PizzaType type, PizzaSize size, HashSet<Toppings> toppings) {
		return staticCalculatePrice(type, size, toppings);
	}

	public Double calculatePrice(Pizza p) {
		return staticCalculatePrice(p);
	}

	public Double calculatePrice(Order o) {
		return staticCalculatePrice(o);
	}
}
