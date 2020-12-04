package com.pizzeria.training.models;

public enum PizzaType {
	CLASSIC		(0, 0.75D), 
	THIN_CRUST	(1, 0.5D), 
	DEEP_DISH	(2, 3.0D), 
	SICILIAN	(3, 1.5D), 
	STUFFED		(4, 2.0D),
	GLUTEN_FREE	(5, 0.5D);
	
	private int index;
	private double height;
	
	PizzaType(int index, double height) {
		this.index = index;
		this.height = height;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public double getHeight() {
		return this.height;
	}
}
