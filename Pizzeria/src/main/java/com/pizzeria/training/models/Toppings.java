package com.pizzeria.training.models;

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
	
	private double costPerSize;
	private boolean isMeat;
	private boolean isVegan;
	
	Toppings(double costPerSize, boolean isMeat, boolean isVegan){
		this.costPerSize = costPerSize;
		this.isMeat = isMeat;
		this.isVegan = isVegan;
	}
	
	public double getCostPerSize() {
		return this.costPerSize;
	}
	
	public boolean getIsMeat() {
		return this.isMeat;
	}
	
	public boolean getIsVegan() {
		return this.isVegan;
	}
}
