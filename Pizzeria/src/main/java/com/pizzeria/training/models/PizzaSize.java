package com.pizzeria.training.models;

public enum PizzaSize {
	SLICE	(0, 4), 
	PERSONAL(1, 8), 
	SMALL	(2, 10), 
	MEDIUM	(3, 12), 
	LARGE	(4, 14), 
	XLARGE	(5, 16);
	
	private int index;
	private int diameter;
	
	PizzaSize(int index, int diameter){
		this.index = index;
		this.diameter = diameter;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public int getDiameter() {
		return this.diameter;
	}
}
