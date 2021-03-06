package com.pizzeria.models;

import java.util.List;

public class Pizza {
	private float height;
	private PizzaType type;
	private List<Toppings> toppings;
	private double cost;
	private Size size;
	
	public Pizza() {}
	
	public Pizza(float height, PizzaType type, List<Toppings> toppings, int cost, Size size) {
		super();
		this.height = height;
		this.type = type;
		this.toppings = toppings;
		this.cost = cost;
		this.size = size;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public PizzaType getType() {
		return type;
	}
	public void setType(PizzaType type) {
		this.type = type;
	}
	public List<Toppings> getToppings() {
		return toppings;
	}
	public void setToppings(List<Toppings> toppings) {
		this.toppings = toppings;
	}
	public double getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(height);
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((toppings == null) ? 0 : toppings.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
			return false;
		if (size != other.size)
			return false;
		if (toppings == null) {
			if (other.toppings != null)
				return false;
		} else if (!toppings.equals(other.toppings))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	public double calculateCost() {
		double totalPrice = 0.0d;
		double sizePrice = 0d;
		double typePrice = 0;
		double topPrice = this.toppings.size() * 0.5;
		switch(this.type) {
			case DEEP_DISH:
				typePrice = 5;
				break;
			case THIN_CRUST:
			case SICILIAN:
			case STUFFED:
				typePrice = 2;
				break;
			case GLUTEN_FREE:
				typePrice = 3;
				break;
			default:
				break;
		}
		switch(this.size) {
			case SLICE:
				sizePrice = 2.0;
				break;
			case PERSONAL:
				sizePrice = 6.0;
			case SMALL:
				sizePrice = 12.0;
				break;
			case MEDIUM:
				sizePrice = 15.0;
				break;
			case LARGE:
				sizePrice = 18.0;
				break;
			case XLARGE:
				sizePrice = 22.0;
				break;
		}
		totalPrice = typePrice + sizePrice + topPrice;
		return totalPrice;
	}

	@Override
	public String toString() {
		return "Pizza [height=" + height + ", type=" + type + ", toppings=" + toppings + ", cost=" + cost + ", size="
				+ size + "]";
	}
	
}
