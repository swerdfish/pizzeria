package com.pizzeria.training.models;
/** 
 * Enum for toppings to be put on a a pizza.
 * Includes information for cost, as well as boolean flags for meat/vegan categories.
 */
public enum Toppings {
	ANCHOVY		(1.0D,	true,	false),
	ARTICHOKE	(1.0D,	false,	true), 
	BACON		(1.0D,	true,	false), 
	BASIL		(1.0D,	false,	true),
	BROCCOLI	(1.0D,	false,	true), 
	CHEESE		(1.0D,	false,	false), 
	CARROT		(1.0D,	false,	true), 
	CHICKEN		(1.0D,	true,	false), 
	CILANTRO	(1.0D,	false,	true), 
	CROUTON		(1.0D,	false,	true), 
	GARLIC		(1.0D,	false,	true), 
	HAM			(1.0D,	true,	false),
	JALAPENO	(1.0D,	false,	true), 
	LETTUCE		(1.0D,	false,	true), 
	MEATBALL	(1.0D,	true,	false), 	
	MUSHROOM	(1.0D,	false,	true), 
	OLIVE		(1.0D,	false,	true), 
	ONION		(1.0D,	false,	true), 
	OREGANO		(1.0D,	false,	true),
	PASTA		(1.0D,	false,	true), 
	PEPPER		(1.0D,	false,	true), 
	PEPPERONI	(1.0D,	true,	false),
	PINEAPPLE	(1.0D,	false,	true), 
	SAUSAGE		(1.0D,	true,	false), 
	SALAMI		(1.0D,	true,	false), 
	SPINACH		(1.0D,	false,	true),
	TOMATO		(1.0D,	false,	true);
	/** Cost per topping unit */
	private double costPerSize;
	/** flag whether the topping is meat*/
	private boolean isMeat;
	/** flag whether the topping is vegan*/
	private boolean isVegan;
	/**
	 * Constructor for topping object
	 * @param costPerSize Cost per topping unit
	 * @param isMeat Indicates the topping is a meat
	 * @param isVegan Indicates the topping is vegan
	 */
	Toppings(double costPerSize, boolean isMeat, boolean isVegan){
		this.costPerSize = costPerSize;
		this.isMeat = isMeat;
		this.isVegan = isVegan;
	}
	/**
	 * Returns the unit cost of the topping
	 * @return Topping price
	 */
	public double getCostPerSize() {
		return this.costPerSize;
	}
	/**
	 * Returns Indicator if topping is a meat
	 * @return True if the topping is a meat, otherwise false
	 */
	public boolean getIsMeat() {
		return this.isMeat;
	}
	/**
	 * Returns indicator if the topping is vegan
	 * @return True if the topping is vegan, otherwise false
	 */
	public boolean getIsVegan() {
		return this.isVegan;
	}
}
