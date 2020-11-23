package com.pizzeria.training.models;

public enum OrderType {
	DELIVERY(4.99), 
	PICKUP(0.00), 
	DINE_IN(0.00), 
	CATERING(0.00);
	
	private double extraCost;
	
	OrderType(double extraCost) {
		this.extraCost = extraCost;
	}
	
	public double getExtraCost() {
		return this.extraCost;
	}
}
